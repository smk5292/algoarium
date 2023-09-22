import pandas as pd
from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Problem, ProblemTag, TagCorrelation
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import NearestNeighbors
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from .serializers import ProblemSerializer, ProblemTagSerializer
from collections import defaultdict
from django.db import transaction
from itertools import combinations
from scipy.stats import pearsonr

@api_view(['POST'])
def test_recommend_problem(request):
    problemdata = Problem.objects.all()
    # bj_user_data = 백준.objects.get(request.유저id)
    # if bj_user_data == null:
    #     # 쉬운문제 랜덤추천
    #     return
    # else:
    #     bj_user_data.tier



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
                tag_correlations.append((key1,key2,jaccard))


    # 계산된 상관 계수를 데이터베이스에 저장
    for tag1, tag2, correlation_coefficient in tag_correlations:
        tag_correlation = TagCorrelation(tag1_id=tag1, tag2_id=tag2, correlation_coefficient=correlation_coefficient)
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


