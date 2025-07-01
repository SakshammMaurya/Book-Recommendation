from flask import Flask, request, jsonify
import pickle
import numpy as np
import pandas as pd
import os

app = Flask(__name__)

similarity = pickle.load(open( 'similarity.pkl', 'rb'))
popularity_df = pickle.load(open('popularity_df.pkl', 'rb'))
final_ratings = pickle.load(open('final_ratings.pkl', 'rb'))


@app.route('/')
def index():
    return "Book Recommender API is running!"


@app.route('/popular', methods=['GET'])
def get_popular():
    data = []

    for _, row in popularity_df.iterrows():
        data.append({
            'title': str(row['Book-Title']),
            'author': str(row['Book-Author']),
            'image': str(row['Image-URL-M']),
            'year': int(row['Year-Of-Publication']),
            'ratings': int(row['avg_rating'])
        })

    return jsonify(data)


@app.route('/recommend', methods=['POST'])
def recommend():
    data = request.get_json()
    book_title = data.get('book_title')

    # Create a case-insensitive mapping
    title_map = {title.lower(): title for title in final_ratings['Book-Title'].values}

    if book_title.lower() not in title_map:
        return jsonify({'error': 'Book not found'}), 404

    actual_title = title_map[book_title.lower()]

    try:
        index = np.where(final_ratings['Book-Title'] == actual_title)[0][0]
        distances = similarity[index]
        book_list = sorted(list(enumerate(distances)), reverse=True, key=lambda x: x[1])[1:6]

        recommendations = []

        for i in book_list:
            row = final_ratings.iloc[i[0]]
            recommendations.append({
                'title': str(row['Book-Title']),
                'author': str(row['Book-Author']),
                'image': str(row['Image-URL-M']),
                'year': int(row['Year-Of-Publication'])
            })

        return jsonify(recommendations)

    except Exception as e:
        return jsonify({'error': str(e)}), 500


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')

