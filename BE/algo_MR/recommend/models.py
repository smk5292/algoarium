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
    problem_level = models.IntegerField()
    sovled_user_count = models.IntegerField()
    problem_point = models.IntegerField()
    users = models.ManyToManyField('BeakJoonUser')

    class Meta:
        db_table = 'problem'

class Tag(models.Model):
    tag_name = models.CharField(max_length=255)
    problems = models.ManyToManyField('Problem')
    
    class Meta:
        db_table = 'tag'


class TagCorrelation(models.Model):
    tag1 = models.ForeignKey(Tag, on_delete=models.CASCADE, related_name='tag1_correlations')
    tag2 = models.ForeignKey(Tag, on_delete=models.CASCADE, related_name='tag2_correlations')
    correlation_coefficient = models.FloatField()

    class Meta:
        db_table = 'tag_correlation'



