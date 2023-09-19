from rest_framework import serializers
from .models import BeakjoonUser, Problem, Tag

class BeakjoonUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = BeakjoonUser
        fields = ("__all__")
        #fields = (bj_id,solved_count,tier)

class ProblemSerializer(serializers.ModelSerializer):
    class Meta:
        model = Problem
        fields = ("__all__")

class TagSerializer(serializers.ModelSerializer):
    class Meta:
        model = Tag
        fields = ("__all__")
