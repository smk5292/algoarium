import pandas as pd
from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Problem, ProblemTag, TagCorrelation, BaekjoonUser, SolvedProblemHistory, RecommendProblem
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import NearestNeighbors
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from .serializers import ProblemSerializer, ProblemTagSerializer
from collections import defaultdict
from django.db import transaction
from itertools import combinations
from scipy.stats import pearsonr
import random

@api_view(['POST'])
def test_recommend_problem(request):
    bj_user_data = BaekjoonUser.objects.filter(bj_id == request.solved_ac_id).first()
    
    if not bj_user_data.exists():
        # 쉬운문제 랜덤추천
        return
    else:
        #유저가 푼 상위100문제정보
        bj_user_solved_problems = SolvedProblemHistory.object.filter(user_id = bj_user_data[0].user_id).order_by('-Problem_level', 'solved_user_count')[:100]
        
        # 유저가 푼문제에 대한 태크 딕셔너리
        solved_problems_tags_dict = defaultdict(set)
        for problem in bj_user_solved_problems:
            problem_id = problem.problem_id
            solved_problem_tags = ProblemTag.objects.filter(problem_id = problem_id)
            for tag in solved_problem_tags:
                solved_problems_tags_dict[problem_id].add(tag.tag_id)
            # solved_problems_tags_dict[problem_id].add(solved_problem_tags.values_list('tag_id', flat=True))

        # 유저티어정보
        user_tier_data = bj_user_data.tier
        # 유저레벨에적합한 레벨문제 1차 필터링
        first_filtered_problems_strong = Problem.objects.filter(problem_level__in=[user_tier_data, user_tier_data+1, user_tier_data+2])
        # first_filtered_problems_weak = Problem.objects.filter(problem_level__in=[user_tier_data-2, user_tier_data-3, user_tier_data-4, user_tier_data-5])

        # 1차필터링 된 문제들의태그들
        first_filtered_problems_tags_dict = defaultdict(set)
        for problem in first_filtered_problems_strong:
            problem_id = problem.problem_id
            problem_tags = ProblemTag.objects.filter(problem_id = problem_id)
            first_filtered_problems_tags_dict[problem_id].add(problem_tags.values_list('tag_id', flat=True))
        

        recommended_problems = []

        # 유사성 측정 및 추천
        for problem_index, problem_tags in enumerate(first_filtered_problems_tags_dict):
            # 유저가 이미 푼 문제는 스킵(index비교)
            if problem_index in solved_problems_tags_dict:
                continue
            
            # 자카드 유사도 계산 (0부터 1 사이의 값)
            similarity = len(solved_problems_tags_dict.values[-1].intersection(problem_tags)) / len(solved_problems_tags_dict[-1].union(problem_tags))
            
            # 추천할 문제에 추가
            recommended_problems.append((problem_index, similarity))

        # 유사도가 높은 순으로 정렬
        recommended_problems.sort(key=lambda x: x[1], reverse=True)

        # 상위 n개의 문제를 추천
        n_recommendations = 100
        top_recommendations = recommended_problems[:n_recommendations]

        # 랜덤 30개 db에 저장
        random_recommendations = random.sample(top_recommendations, 30)

    for last_recommend_problem_data  in random_recommendations:
        recommend_problem = RecommendProblem(problem=last_recommend_problem_data, user=bj_user_data.id, type = 0)
        recommend_problem.save()    

        


@api_view(['POST'])
def problem_correlation(request):
    # 중계 테이블에서 데이터를 가져옵니다.
    problem_tags = ProblemTag.objects.all()

    # Problem을 기준으로 Tag를 그룹화하기 위한 딕셔너리 생성
    problem_tag_dict = defaultdict(set)

    # 중계 테이블 데이터를 딕셔너리에 그룹화합니다.
    for problem_tag in problem_tags:
        problem_id = problem_tag.problem_id
        tag_id = problem_tag.tag_id
        problem_tag_dict[tag_id].add(problem_id)

    tag_lists = list(problem_tag_dict.values())
    # 각 태그 리스트 간의 상관 계수 계산
      # 각 태그의 등장 횟수 계산
    # problem_counts = defaultdict(int)
    # for problems in problem_tag_dict.values():
    #     for problem in problems:
    #         problem_counts[problem] += 1

    # print(problem_tag_dict[1])
    tag_correlations = []
    for key1, value1 in problem_tag_dict.items():
        for key2, value2 in problem_tag_dict.items():
            if key1 < key2:
                jaccard = jaccard_similarity(value1, value2)
                consine = cosine_similarity(value1, value2)
                tag_correlations.append((key1,key2,jaccard,consine))


    # 계산된 상관 계수를 데이터베이스에 저장
    for tag1, tag2, jaccard_correlation, consine_correlation in tag_correlations:
        tag_correlation = TagCorrelation(tag1_id=tag1, tag2_id=tag2, jaccard_correlation = jaccard_correlation, consine_correlation = consine_correlation)
        tag_correlation.save()

    for tag1, tag2, jaccard_correlation, consine_correlation in tag_correlations:
        tag_correlation = TagCorrelation(tag1_id=tag1, tag2_id=tag2, jaccard_correlation = jaccard_correlation, consine_correlation = consine_correlation)
        tag_correlation.save()    

    # 처리 결과를 응답으로 반환
    return Response({"message": "Tag correlations calculated and saved successfully."})


# 두 집합의 자카드 유사도 계산
def jaccard_similarity(set1, set2):
    intersection = len(set1.intersection(set2))
    union = len(set1.union(set2))
    similarity = intersection / union
    return similarity

# 두 집합의 코사인 유사도 계산
def cosine_similarity(set1, set2):
    intersection = len(set1.intersection(set2))
    norm1 = len(set1)
    norm2 = len(set2)
    similarity = intersection / (norm1 * norm2)
    return similarity


