from django.db import models

# Create your models here.
class BeakjoonUser(models.Model):
    bj_id = models.CharField(max_length=100)
    solved_count = models.IntegerField()
    bj_class = models.IntegerField()
    tier = models.IntegerField()
    rating =models.IntegerField()
    rating_by_problem_sum = models.IntegerField()
    rating_by_class = models.IntegerField()
    rating_by_solved = models.IntegerField()
    rank = models.IntegerField()

    class Meta:
        db_table = 'beakjoon_user'


class Problem(models.Model):
    title = models.CharField(max_length=100)
    problem_tag = models.CharField(max_length=100)
    problem_level = models.IntegerField()
    sovled_user_count = models.IntegerField()
    problem_point = models.IntegerField()

    class Meta:
        db_table = 'problem'


