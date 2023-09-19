import pandas as pd
from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializers import BeakjoonUserSerializer
from .models import BeakjoonUser, Problem
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import NearestNeighbors
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel


@api_view(['GET'])
def test_recommend_problem(request):
    beakjoon_users = BeakjoonUser.objects.all() 
    problems = Problem.objects.all()
    
    # 사용자가 푼 문제와 각 문제의 특징 데이터 준비
    user_solved_problems = {}  # 사용자가 푼 문제 저장
    problem_features = {}  # 문제 특징 데이터 저장

    user1 = BeakjoonUser.objects.get(pk=1)  # 사용자 객체 가져오기
    solved_problems = user1.problem_set.all()  # 사용자가 푼 모든 문제 가져오기
    print("1: 사용자가 푼 문제")
    print(solved_problems)
    # for user in beakjoon_users:
    #     solved_problems = user.problem_set.all()
    #     user_solved_problems[user.pk] = [problem.title for problem in solved_problems]
    user_solved_problems[user1.pk] = [problem.id for problem in solved_problems]
    print("2. 사용자가 푼 문제번호만")
    print(user_solved_problems[user1.pk])
    for problem in problems:
        tags = problem.tag_set.all()
        problem_features[problem.id] = ' '.join([tag.tag_name for tag in tags])
    # TF-IDF 벡터화
    tfidf_vectorizer = TfidfVectorizer()
    tfidf_matrix = tfidf_vectorizer.fit_transform(list(problem_features.values()))
    print(tfidf_matrix)
    # 추천할 사용자 선택 (예시: 첫 번째 사용자)
    # target_user = beakjoon_users[0]

    # 사용자가 푼 문제 제외하고 유사한 문제 찾기
    user_solved_problem_titles = user_solved_problems[user1.pk]
    candidate_problems = Problem.objects.exclude(title__in=user_solved_problem_titles)

    # 사용자와 각 후보 문제의 유사도 계산
    cosine_similarities = linear_kernel(tfidf_vectorizer.transform([problem_features[p.title] for p in candidate_problems]), tfidf_vectorizer.transform([problem_features[p] for p in user_solved_problem_titles]))

    # 가장 유사한 문제 선택
    # best_match_index = cosine_similarities.argmax()
    # recommended_problem = candidate_problems[best_match_index]
    best_match_index = int(cosine_similarities.argmax())
    # recommended_problem = candidate_problems[best_match_index]
    if best_match_index < len(candidate_problems):
        recommended_problem = candidate_problems[best_match_index]
    else:
        # 후보 문제가 없을 경우에 대한 처리
        recommended_problem = None  # 또는 다른 기본값을 설정할 수 있음

    if recommended_problem:
        response_data = {
            'recommended_problem_title': recommended_problem.title,
            'cosine_similarity': cosine_similarities[best_match_index][0]
        }
    else:
        response_data = {
            'recommended_problem_title': 'No recommendation available',
            'cosine_similarity': 0  # 또는 다른 기본값을 설정할 수 있음
        }



    # 추천 문제 반환
    response_data = {
        'recommended_problem_title': recommended_problem.title,
        'cosine_similarity': cosine_similarities[best_match_index][0]
    }

    return Response(response_data)