


# Book Recommendation System 

## Table of Content
  * [Demo](#demo)
  * [Overview](#overview)
  * [Technical Aspect](#technical-aspect)
  * [🚀 Deployement](#deployement-on-heroku)
  * [Directory Tree](#directory-tree)
  * [Technologies Used](#technologies-used)
  * [Team](#team)
  * [Credits](#credits)




## Demo

<p align="center">
  <a href="https://ibb.co/j9x8Rrfd"><img src="https://i.ibb.co/vCrcYjqb/Screenshot-2025-07-01-10-50-39-802-com-example-bookrecommendation.jpg" alt="Screenshot-2025-07-01-10-50-39-802-com-example-bookrecommendation" height="500" widht ="30%"></a>
  <a href="https://ibb.co/ZzxNF5CQ"><img src="https://i.ibb.co/QvMXTLGW/Screenshot-2025-07-01-10-51-13-760-com-example-bookrecommendation.jpg" alt="Screenshot-2025-07-01-10-51-13-760-com-example-bookrecommendation" height="500" widht ="30%"></a>
  <a href="https://ibb.co/N6sGpXNx"><img src="https://i.ibb.co/kgxP0pDK/Screenshot-2025-07-01-10-50-52-524-com-example-bookrecommendation.jpg" alt="Screenshot-2025-07-01-10-50-52-524-com-example-bookrecommendation" height="500" widht ="30%"></a>
</p>


## Overview
This is a learning-focused project built to explore the complete development pipeline of a book recommendation system — from model creation to API development and an Android app frontend.

It uses collaborative filtering to recommend books based on user preferences and includes a Flask API to serve the model and a Jetpack Compose Android app to display popular and recommended books.


## Technical Aspect
This project is divided into three parts:
1. Training a machine learning model.
   - Collaborative filtering was used to recommend books based on user preferences.
   - Data was preprocessed and stored using pickled .pkl files for performance.
2. Building and hosting a Flask Android app.
    - A __/popular__ route returns a list of the top 50 most popular books.
    - A __/recommend__ route takes a book title as input and returns 5 similar book recommendations.
3. Developing an Android App using Jetpack Compose.
     - The home screen displays popular books with their cover, title, author, and rating.
     - Users can search for a book, and if found, receive 5 recommended books.
     - __Retrofit__ is used for seamless API communication.
     - Image loading is handled using __Coil__.
     - __Real-time error handling__ with appropriate messages like “Book not found” or “Network error”.
  

## 🚀 Deployement
This project is built and tested locally. Below is how you can deploy and run both components:

### Flask Backend (API)

- Make sure Python and the required packages (Flask, numpy, pandas, etc.) are installed.

- Navigate to the Flask API directory.
- Run the server:
```bash
python app.py
 ```
###  Android App

- Open the Android project in Android Studio.
- Make sure your emulator or device is connected.
- Update the base URL in Retrofit if needed (for emulator).
- Build and run the app.



## Directory Tree 
```
Book Recommendation System/
├── Android app/
│   └── BookRecommendation2/
│       ├── app/
│       ├── build.gradle.kts
│       ├── gradle/
│       ├── settings.gradle.kts
│       ├── .idea/
│       └── ...
├── Flask API/
│   ├── app.py
│   ├── requirements.txt
│   └── ...
├── Model/
│   ├── popularity_df.pkl
│   ├── final_ratings.pkl
│   ├── similarity.pkl
│   └── ...
├── README.md
└── .gitignore

```


## Technologies Used

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" height="40" alt="kotlin logo"  />
  <img width="12" />
  <img src="https://cdn.simpleicons.org/android/3DDC84" height="40" alt="android logo"  />
  <img width="12" />
  <img src="https://cdn.simpleicons.org/androidstudio/3DDC84" height="40" alt="androidstudio logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jetpackcompose/jetpackcompose-original.svg" height="40" alt="jetpackcompose logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/python/python-original.svg" height="40" alt="python logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/pandas/pandas-original.svg" height="40" alt="pandas logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/numpy/numpy-original.svg" height="40" alt="numpy logo"  />
  <img width="12" />
  <img src="https://cdn.simpleicons.org/anaconda/44A833" height="40" alt="anaconda logo"  />
  <img width="12" />
  <img src="https://skillicons.dev/icons?i=pycharm" height="40" alt="pycharm logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/flask/flask-original.svg" height="40" alt="flask logo"  />
  <img width="12" />
  <img src="https://cdn.simpleicons.org/postman/FF6C37" height="40" alt="postman logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" height="40" alt="git logo"  />
</div>

## Team
<a href="https://ibb.co/p6B1zx13"><img src="https://i.ibb.co/6c7yPmy4/Whats-App-Image-2025-07-01-at-13-26-16-37a821cc.jpg" alt="my image" border="0" height ="150" /></a> |
-|
Saksham Maurya


## Credits
This project was inspired and supported by the following resources:

### Youtube tutorials
- [Book Recommender System | Machine Learning Project]("https://youtu.be/1YoD0fg3_EM?si=YlJX1LqapFVZh2Cj")
- [Machine Learning Android App]("https://youtu.be/ax3WyB-_LJY?si=QtsZgqHEoLnW8KoV")
- [Complete REST API tutorial in flask]("https://youtu.be/mWrvI0hfllI?si=rFN7kDn8N7KTHDKM")

### Datasets
- [Books dataset on Kaggle]("https://www.kaggle.com/datasets/saurabhbagchi/books-dataset")
