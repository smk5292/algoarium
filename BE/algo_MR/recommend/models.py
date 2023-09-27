# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models

class BaekjoonUser(models.Model):
    bj_user_id = models.AutoField(primary_key=True)
    bj_class = models.IntegerField(blank=True, null=True)
    bj_id = models.CharField(max_length=100)
    rank = models.IntegerField(blank=True, null=True)
    rating = models.IntegerField(blank=True, null=True)
    rating_by_class = models.IntegerField(blank=True, null=True)
    rating_by_problem_sum = models.IntegerField(blank=True, null=True)
    rating_by_solved_count = models.IntegerField(blank=True, null=True)
    solved_count = models.IntegerField(blank=True, null=True)
    tier = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'baekjoon_user'


class Problem(models.Model):
    problem_id = models.AutoField(primary_key=True)
    problem_level = models.IntegerField()
    problem_number = models.IntegerField()
    problem_tag = models.CharField(max_length=100)
    solved_user_count = models.IntegerField()
    title = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'problem'


class ProblemLike(models.Model):
    problem_like_id = models.AutoField(primary_key=True)
    like_type = models.TextField()  # This field type is a guess.
    memo = models.TextField()
    problem = models.ForeignKey(Problem, models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey('User', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'problem_like'


class ProblemTag(models.Model):
    problem_tag_id = models.AutoField(primary_key=True)
    problem = models.ForeignKey(Problem, models.DO_NOTHING, blank=True, null=True)
    tag = models.ForeignKey('Tag', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'problem_tag'


class Tag(models.Model):
    tag_id = models.BigAutoField(primary_key=True)
    tag_name = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'tag'


class User(models.Model):
    user_id = models.BigAutoField(primary_key=True)
    kakao_id = models.CharField(max_length=100)
    kakao_nickname = models.CharField(max_length=50)
    pre_tier = models.CharField(max_length=20)
    profile_image = models.CharField(max_length=200)
    refresh_token = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'user'

class TagCorrelation(models.Model):
    tag1 = models.ForeignKey(Tag, on_delete=models.CASCADE, related_name='tag1_correlations')
    tag2 = models.ForeignKey(Tag, on_delete=models.CASCADE, related_name='tag2_correlations')
    jaccard_correlation = models.FloatField()
    consine_correlation = models.FloatField()

    class Meta:
        db_table = 'tag_correlation'
