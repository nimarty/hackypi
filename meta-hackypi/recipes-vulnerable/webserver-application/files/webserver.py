#!/usr/bin/env python

import time

from flask import Flask, request
from flask_restful import Resource, Api
from flask import jsonify, make_response, render_template
import random

print("""REST server providing temperature and pressure, based on random values.

Press Ctrl+C to exit

""")


app = Flask(__name__)
api = Api(app)

class Temperature(Resource):
    def get(self):
        result = {'temperature': '{0:.2f} C'.format(random.random()*30)}
        return jsonify(result)

class Pressure(Resource):
    def get(self):
        result = {'pressure': '{0:.2f} hPa'.format(random.random()*30+990)}
        return jsonify(result)

class Index(Resource):
    def get(self):
        headers = {'Content-Type': 'text/html'}
        # render_template('index.html')
        return make_response("<h1>Hello World</h1>",200,headers)

api.add_resource(Temperature, '/temperature') # Route_1
api.add_resource(Pressure, '/pressure') # Route_2
api.add_resource(Index, '/')

if __name__ == '__main__':
     app.run(port='5000', host='0.0.0.0')
