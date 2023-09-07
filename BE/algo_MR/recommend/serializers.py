from rest_framework import serializers
from .models import BeakjoonUser

class BeakjoonUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = BeakjoonUser
        fields = ("__all__")
        #fields = (bj_id,solved_count,tier)