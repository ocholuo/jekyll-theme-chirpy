#~movie-bag/resources/movie.py

from flask import Response, request
from flask_restful import Resource
from database.models import Movie


class User(self, username, FLname, password, engine):
    self.username = username
    self.FLname = FLname
    self.password = password
    self.engine = enginee


class MoviesApi(Resource):
  def get(self):
    movies = Movie.objects().to_json()
    return Response(movies, mimetype="application/json", status=200)

  def post(self):
    body = request.get_json()
    movie = Movie(**body).save()
    id = movie.id
    return {'id': str(id)}, 200
 
class MovieApi(Resource):
  def put(self, id):
    body = request.get_json()
    Movie.objects.get(id=id).update(**body)
    return '', 200
 
  def delete(self, id):
    movie = Movie.objects.get(id=id).delete()
    return '', 200

  def get(self, id):
    movies = Movie.objects.get(id=id).to_json()
    return Response(movies, mimetype="application/json", status=200)
