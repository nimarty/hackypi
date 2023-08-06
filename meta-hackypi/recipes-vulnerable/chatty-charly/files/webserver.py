#!/usr/bin/env python3

import time

from flask import Flask, request, url_for
from flask_restful import Resource, Api, reqparse
from flask import jsonify, make_response, render_template

from subprocess import PIPE, run

print("""REST server providing system logs.

Press Ctrl+C to exit

""")


app = Flask(__name__)
api = Api(app)

class Logs(Resource):
    def get(self):
        headers = {'Content-Type': 'text/plain'}
        parser = reqparse.RequestParser()
        parser.add_argument('filepath', type=str, required=True, location='args', 
            help="filepath cannot be blank!")
        params = parser.parse_args()
        command = ['cat', params['filepath']]
        result = run(command, stdout=PIPE, stderr=PIPE, universal_newlines=True)
        if not result.stderr:
            return make_response(result.stdout, 200, headers)
        else:
            return make_response(result.stderr, 400, headers)

class Index(Resource):
    def get(self):
        headers = {'Content-Type': 'text/html'}
        # render_template('index.html')
        logsurl = url_for('logs', _external=True)
        logsurl += '?filepath=/var/log/messages'
        html = '<h1>Logserver</h1><a href="{}"/>Show Logs</a>'.format(logsurl)
        return make_response(html, 200, headers)

api.add_resource(Logs, '/logs') # Route_1
api.add_resource(Index, '/')

if __name__ == '__main__':
     app.run(port='8088', host='0.0.0.0')
