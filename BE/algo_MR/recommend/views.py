import pandas as pd
from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.response import Response
from .serializers import BeakjoonUserSerializer
from .models import BeakjoonUser
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import NearestNeighbors

# Create your views here.
class BeakjoonUserViewSet(viewsets.ModelViewSet):
    serializer_class = BeakjoonUserSerializer

    def knn_recommend_users(self, request):
        solved_count = int(request.query_params.get('solved_count', 0))
        tier = int(request.query_params.get('tier', 0))
        rating = int(request.query_params.get('rating', 0))
        rank = int(request.query_params.get('rank', 0))
        bj_class = int(request.query_params.get('bj_class', 0))

        beakjoon_user = BeakjoonUser.objects.all()
        data = pd.DataFrame(list(beakjoon_user.values('solved_count', 'tier', 'rating', 'rank', 'bj_class')))

        # 새로운 데이터 스케일링
        new_data = [[solved_count, tier, rating, rank, bj_class]]
        scaler = StandardScaler()
        data_scaled = scaler.fit_transform(data)
        new_data_scaled = scaler.transform(new_data)

        # KNN 모델로 유저 추천
        knn = NearestNeighbors(n_neighbors=5)  # 이웃의 수 설정
        knn.fit(data_scaled)  # 데이터셋은 이전에 사용한 데이터셋(data_scaled)과 동일해야 함
        distances, indices = knn.kneighbors(new_data_scaled)

        # 추천된 유저 가져오기
        recommended_users = [beakjoon_user[i] for i in indices[0]]

        serializer = BeakjoonUserSerializer(recommended_users, many=True)
        return Response(serializer.data)