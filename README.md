* [Quiz API](#quiz-api)
* [Tech Stack](#tech-stack)
* [Features](#application-view)
* [Setup](#setup)
* [Endpoints](#Endpoints)

# Quiz API

The project is about making an api differently from REST in graphql, the API is based on creating questions from the game Leauge of Legends. Questions can be created in various combinations with ABCD options, and if a question needs to be visualised, there is an option to add a photo or video and sound.

## Tech Stack

**Server:** Graphql, Spring, MongoDB, Lombok


## Features

- Create question
- Create question from JSON File
- Upload files to question like images, video or audio
- Delete question
- Update question

## Setup
Clone the repo
``` git clone https://github.com/R3venge1337/QuizAppApi.git```

## Endpoints
Type Query is similar to GET method in http, using for fetching data
```
type Query {
	getAllRoles: [Role]
	getRoleByName(roleName: ERole): Role
	getAllUsers(limit: Int): [User]
	findByUsername(username: String): User
	findAllDifficulties: [Difficulty]
	getDifficultyByName(difficultyName: EDifficulty): Difficulty
	getCategoryByName(categoryName: ECategory): Category
	findAllCategories: [Category]
	findAllQuestions(limit: Int): [Question]
	getImage(id: String): Image
	getAudio(id: String): Audio
	getVideo(id: String): Video
	findAllLanguages:[Language]
	findLanguageByName(languageName: ELanguage): Language
	findQuestionById(id: String):Question
}
```
Type Mutation is similar to POST methond in http, using for creating data
```
  createRole(role: RoleInput): Role
	createUser(user: UserInput): User
	createCategory(name: CategoryInput): Category
	createLanguage(name: LanguageInput): Language
	createDifficulty(name: DifficultyInput): Difficulty
	createQuestion(question: QuestionInput,imageFile:Upload,audioFile:Upload,videoFile:Upload): Question
	createQuestionFromJsonFile(file:Upload): Boolean
	updateQuestion(id:String,questionUpdate: QuestionUpdateInput): Question
	registerUser(user: registerUserInput): String
	authenticateUser(user: loginUserInput): JwtResponse
	addImage(file: Upload): String
	addAudio(file: Upload): String
	addVideo(file: Upload): String
	addRoleToUser(username:String,role:RoleInput):User
	deleteAllQuestions: Boolean
	deleteUserRole(username:String,role:RoleInput):User
	deleteQuestionById(id:String):Boolean
```
Example of adding question with files in postman, you need to check form-data and pass some keys 
```
http://localhost:8080/graphql
operations:  {"query":"mutation($imageFile:Upload,$audioFile:Upload,$videoFile:Upload) {
    createQuestion(question: {
      questionName: "ef",
      answerA: "eqffq",
      answerB: "efqqf",
      answerC: "eqfefq",
      answerD: "efqfqe",
      correctAnswer: "eqffq",
      language: {
        name: PL
      },
      category: {
        name: GAMEPLAY
      },
      difficulty: {
        name: EASY
      },
    },imageFile: $imageFile,audioFile: $audioFile,videoFile: $videoFile){
      questionName
  }
  ","variables": {"imageFile": null,"audioFile": null, "videoFile": null }}
map: {"0":["variables.imageFile"],"1":["variables.audioFile"],"2":["variables.videoFile"]}
0: volume.png 
1: audio.mp3
2: video.m4v
```

