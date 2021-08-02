import psycopg2
from config import config

def notes_connection():
    try:
        params = config()
        connection = psycopg2.connect(**params)
        return connection
    except(Exception, psycopg2.DatabaseError) as error:
        print(error)


def create_notes(note):
    try:
        connection = notes_connection()
        cursor = connection.cursor()
        note_query = """INSERT INTO accountnotes (notes) VALUES (%s)"""
        cursor.execute(note_query, note)
        connection.commit()
    except(Exception, psycopg2.Error) as error:
        print(error)


def read_notes():
    try:
        connection = notes_connection()
        cursor = connection.cursor()
        read_query = """SELECT * FROM accountnotes"""
        cursor.execute(read_query)
        connection.commit()
        result = cursor.fetchall()
        print(result)
    except(Exception, psycopg2.Error) as error:
        print(error)
