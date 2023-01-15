package com.lukabakula.nogometnikvizapp

object Constants {

    const val USER_NAME: String="user_name"
    const val TOTAL_QUESTIONS: String="total_questions"
    const val CORRECT_ANSWERS: String="correct_answers"
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1, "Who was the top scorer at the 2022 World Cup?\n",
            R.drawable.kviz,
            "Leo Messi", "Kylian Mbappe",
            "Olivier Giroud", "Cristiano Ronaldo", 2
        )

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "Which country has won the most world cups?",
            R.drawable.kviz,
            "France", "Germany",
            "Brazil", "Argentina", 3
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "Which of the mentioned players has never played in the Champions League\n",
            R.drawable.kviz,
            "Diego Maradona", "Luis Figo",
            "Ronaldo Nazario", "Pele", 4
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "Who is the only Brazilian who has not won the Balloon d'Or?",
            R.drawable.kviz,
            "Ronaldinho", "Neymar",
            "Pele", "Rivaldo", 2
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "Which is the only African country that made it to the playoffs of the World Cup?",
            R.drawable.kviz,
            "Ghana", "Nigeria",
            "Morocco", "Cameroon", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "Who is the only player who has won 2 golden balls on the world cups\n",
            R.drawable.kviz,
            "Leo Messi", "Pele",
            "Miroslav Klose", "none of these", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "Who is the all time World Cup top scorer?",
            R.drawable.kviz,
            "Lionel Messi", "Pele",
            "Miroslav Klose", "Ronaldinho", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "Which player is France's best goalscorer ever?\n",
            R.drawable.kviz,
            "Zidane", "Thiery Henry",
            "Kylian Mbappe", "Olivier Giroud", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "Which club has the most Champions League trophies?\n",
            R.drawable.kviz,
            "Barcelona", "Real Madrid",
            "Milan", "Liverpool", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, "Which club has the most Premier League titles ever?",
            R.drawable.kviz,
            "Manchester City", "Liverpool",
            "Chelsea", "Manchester United", 4
        )

        questionsList.add(que10)

        return questionsList
    }
}