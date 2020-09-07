package com.sabo.dominik.quizalarm;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class Question implements Serializable {

    private String question;
    private String correct;
    private ArrayList<String> incorrect;

    public Question(){}

    public Question(String question, String correct, ArrayList<String> incorrect) {
        this.question = question;
        this.correct = correct;
        this.incorrect = incorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect() {
        return correct;
    }

    public ArrayList<String> prepareQuestion(int difficulty){
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(this.correct);
        Collections.shuffle(this.incorrect);
        for(int i = 0; i<difficulty + 2; i++){
            answers.add(this.incorrect.get(i));
        }
        Collections.shuffle(answers);
        return answers;
    }

    public void createQuestions(Context context) throws IOException {
        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<String> incorrects;

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Cheetah");
        incorrects.set(1, "Brown Hare");
        incorrects.set(2, "Sail Fish");
        incorrects.set(3, "Pronghorn Antelope");
        questions.add(new Question("Which animal is the fastest in the world?", "Peregrine Falcon", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Red Kangaroo");
        incorrects.set(1, "Grasshopper");
        incorrects.set(2, "Jumping Spider");
        incorrects.set(3, "Hare");
        questions.add(new Question("Which animal is the world's greatest jumper?", "Flea", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Giraffe");
        incorrects.set(1, "Asian Elephant");
        incorrects.set(2, "Hippopotamus");
        incorrects.set(3, "Gray Whale");
        questions.add(new Question("Which is the biggest living animal on Earth?", "Blue Whale", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Black Tiger Snake");
        incorrects.set(1, "Eastern Brown Snake");
        incorrects.set(2, "Peron's Sea Snake");
        incorrects.set(3, "Many-banded Krait");
        questions.add(new Question("Which of the following is the world's most venomous snake?", "Inland Taipan", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Giant Armadillo");
        incorrects.set(1, "Night Monkey");
        incorrects.set(2, "Squirrel");
        incorrects.set(3, "Tiger");
        questions.add(new Question("Which animal of the following sleeps the most?", "Koala", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Goldcrest");
        incorrects.set(1, "Verdin");
        incorrects.set(2, "Willow Tit");
        incorrects.set(3, "Spotted pardalote");
        questions.add(new Question("Which is the smallest bird in the world?", "Bee hummingbird", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Elephant");
        incorrects.set(1, "Dolphin");
        incorrects.set(2, "Goat");
        incorrects.set(3, "Crow");
        questions.add(new Question("Which is the smartest animal in the world?", "Chimpanzee", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Orangutan");
        incorrects.set(1, "Sea Turtles");
        incorrects.set(2, "Gorilla");
        incorrects.set(3, "Rhino");
        questions.add(new Question("Which is the world's most endangered animal species?", "Amur Leopard", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Colombian Giant Tarantula");
        incorrects.set(1, "Camel Spider");
        incorrects.set(2, "Giant Huntsman Spider");
        incorrects.set(3, "Hercules Baboon Spider");
        questions.add(new Question("Which is the biggest spider in the world?", "Goliath Birdeater", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "African Elephant");
        incorrects.set(1, "Donkey");
        incorrects.set(2, "Killer Whale");
        incorrects.set(3, "Camel");
        questions.add(new Question("What animal has the longest pregnancy?", "Frilled Shark", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Dog");
        incorrects.set(1, "Elephant");
        incorrects.set(2, "Owl");
        incorrects.set(3, "Bat");
        questions.add(new Question("What animal has the best hearing?", "Moth", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Kiwi");
        incorrects.set(1, "Trumpeter Swan");
        incorrects.set(2, "Emu");
        incorrects.set(3, "Cassowary");
        questions.add(new Question("Which bird lays the biggest eggs?", "Ostrich", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Ussuri Brown Bear");
        incorrects.set(1, "Grizzly Bear");
        incorrects.set(2, "Giant Panda");
        incorrects.set(3, "Kodiak Bear");
        questions.add(new Question("Which is the largest bear in the world?", "Polar Bear", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Hawks");
        incorrects.set(1, "Owls");
        incorrects.set(2, "Cats");
        incorrects.set(3, "Coyotes");
        questions.add(new Question("Which of the following has the sharpest eyes?", "Eagles", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Garden Snail");
        incorrects.set(1, "Starfish");
        incorrects.set(2, "Giant Tortoise");
        incorrects.set(3, "Koala");
        questions.add(new Question("Which is the slowest animal in the world?", "Three-toed Sloth", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Frilled Shark");
        incorrects.set(1, "Giant Squid");
        incorrects.set(2, "Stargazer");
        incorrects.set(3, "Snaggletooth");
        questions.add(new Question("Which deep sea species has luminous abilities?", "Anglerfish", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "415");
        incorrects.set(1, "207");
        incorrects.set(2, "370");
        incorrects.set(3, "263");
        questions.add(new Question("How many dog breeds exist in the world?", "340", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Marlin");
        incorrects.set(1, "Swordfish");
        incorrects.set(2, "Bluefin Tuna");
        incorrects.set(3, "Killer Whale");
        questions.add(new Question("Which of the following is the world’s fastest swimmer?", "Sailfish", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "300 liters");
        incorrects.set(1, "150 liters");
        incorrects.set(2, "40 liters");
        incorrects.set(3, "120 liters");
        questions.add(new Question("How much water can a camel drink within three minutes?", "200 liters", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Housefly");
        incorrects.set(1, "House Mouse");
        incorrects.set(2, "Chameleon");
        incorrects.set(3, "Dragonfly");
        questions.add(new Question("Which animal has the shortest lifespan?", "Mayfly", incorrects));

        saveQuestionData(questions, "Animals", context);
        questions.clear();


        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Nina Simone");
        incorrects.set(1, "Peter Gabriel");
        incorrects.set(2, "Johnny Cash");
        incorrects.set(3, "The Doors");
        questions.add(new Question("Who of the following was one of the first inductees of the Rock and Roll Hall of Fame?", "Chuck Berry", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Wolfgang Amadeus Mozart");
        incorrects.set(1, "Giuseppe Verdi");
        incorrects.set(2, "Igor Stravinsky");
        incorrects.set(3, "Gustav Mahler");
        questions.add(new Question("Which of the following composers is known to have written nine symphonies?", "Ludwig van Beethoven", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Trumpet");
        incorrects.set(1, "Guitar");
        incorrects.set(2, "Piano");
        incorrects.set(3, "Clarinet");
        questions.add(new Question("Which of these is not just an instrument but also a musical key?", "Violin", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Ed Sheeran");
        incorrects.set(1, "Rihanna");
        incorrects.set(2, "The Supremes");
        incorrects.set(3, "Elvis Presley");
        questions.add(new Question("Which of the following has had the most number one singles of Billboard’s Hot 100?", "The Beatles", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Led Zeppelin – Led Zeppelin IV");
        incorrects.set(1, "Pink Floyd – The Wall");
        incorrects.set(2, "AC/DC – Back in Black");
        incorrects.set(3, "Fleetwood Mac – Rumours");
        questions.add(new Question("Which is the best-selling album in history?", "Eagles – Their Greatest Hits ", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "76");
        incorrects.set(1, "61");
        incorrects.set(2, "52");
        incorrects.set(3, "90");
        questions.add(new Question("What is the typical number of keys on a piano?", "88", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "The Book of Souls");
        incorrects.set(1, "Powerslave");
        incorrects.set(2, "Killers");
        incorrects.set(3, "Fear of the Dark");
        questions.add(new Question("Which of the following is not an album written by Iron Maiden?", "Kill 'Em All", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Djamileh");
        incorrects.set(1, "Jeux d’enfants");
        incorrects.set(2, "Symphony in C");
        incorrects.set(3, "Habanera");
        questions.add(new Question("Which is George Bizet’s most famous musical piece?", "Carmen", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Richard Strauss");
        incorrects.set(1, "Maurice Ravel");
        incorrects.set(2, "Richard Wagner");
        incorrects.set(3, "Sergei Prokofiev");
        questions.add(new Question("Who composed Carmina Burana?", "Carl Orff", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Eminem");
        incorrects.set(1, "Wu-Tang Clan");
        incorrects.set(2, "Dr. Dre");
        incorrects.set(3, "Snoop Dogg");
        questions.add(new Question("Who performs the famous rap song \"Juicy\"?", "The Notorious B.I.G.", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "David Guetta");
        incorrects.set(1, "Avicii");
        incorrects.set(2, "Martin Garrix");
        incorrects.set(3, "Robin Schulz");
        questions.add(new Question("Which DJ is known to have had the most number one hits of the 2010s?", "Calvin Harris", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Baritone");
        incorrects.set(1, "Contralto");
        incorrects.set(2, "Mezzo-Soprano");
        incorrects.set(3, "Tenor");
        questions.add(new Question("Which of the following is the highest voice type?", "Soprano", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Elephant");
        incorrects.set(1, "Dolphin");
        incorrects.set(2, "Goat");
        incorrects.set(3, "Crow");
        questions.add(new Question("Which is the smartest animal in the world?", "Chimpanzee", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Imagine Dragons");
        incorrects.set(1, "Maroon 5");
        incorrects.set(2, "Ed Sheeran");
        incorrects.set(3, "OneRepublic");
        questions.add(new Question("Who sings \"Look at the stars, look how they shine for you and everything you do\"?", "Coldplay", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Gwen Stefani");
        incorrects.set(1, "Beyoncé");
        incorrects.set(2, "Panic! at the Disco");
        incorrects.set(3, "Linkin Park");
        questions.add(new Question("Who won MTV’s Video Music Award for Video of the Year in 2005?", "Green Day", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Elephant");
        incorrects.set(1, "Dolphin");
        incorrects.set(2, "Goat");
        incorrects.set(3, "Crow");
        questions.add(new Question("Which is the smartest animal in the world?", "Chimpanzee", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Chaka Khan");
        incorrects.set(1, "Alicia Keys");
        incorrects.set(2, "Whitney Houston");
        incorrects.set(3, "Elta James");
        questions.add(new Question("Who is otherwise known as the \"Queen of Soul\"?", "Aretha Franklin", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Despacito");
        incorrects.set(1, "That's What I Like");
        incorrects.set(2, "Something Just Like This");
        incorrects.set(3, "Humble.");
        questions.add(new Question("Which song was the biggest hit of 2017?", "Shape of You", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Judas Priest");
        incorrects.set(1, "Motörhead");
        incorrects.set(2, "Deep Purple");
        incorrects.set(3, "Slayer");
        questions.add(new Question("Which band is often cited as the pioneer of heavy metal music?", "Black Sabbath", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Don't Leave Me This Way");
        incorrects.set(1, "Disco Inferno");
        incorrects.set(2, "I Feel Love");
        incorrects.set(3, "I Will Survive");
        questions.add(new Question("Which of the following is a Bee Gee’s song?", "Night Fever", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Bryan Adams");
        incorrects.set(1, "Huey Lewis and The News");
        incorrects.set(2, "Tom Petty");
        incorrects.set(3, "Sting");
        questions.add(new Question("Who sings the song \"Born in the USA\"?", "Bruce Springsteen", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Girlfriend");
        incorrects.set(1, "Complicated");
        incorrects.set(2, "Sk8er Boi");
        incorrects.set(3, "What The Hell");
        questions.add(new Question("Which of the following is not an Avril Lavigne song?", "Toxic", incorrects));

        saveQuestionData(questions, "Music", context);
        questions.clear();


        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "David Niven");
        incorrects.set(1, "Roger Moore");
        incorrects.set(2, "Pierce Brosnan");
        incorrects.set(3, "Timothy Dalton");
        questions.add(new Question("Who was the first James Bond?", "Sean Connery", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "The Untouchables");
        incorrects.set(1, "Heat");
        incorrects.set(2, "Black Mass");
        incorrects.set(3, "Donnie Brasco");
        questions.add(new Question("\"I'm gonna make him an offer he can't refuse.\" is a quote from?", "The Godfather", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Elizabeth Taylor");
        incorrects.set(1, "Audrey Hepburn");
        incorrects.set(2, "Sophia Loren");
        incorrects.set(3, "Grace Kelly");
        questions.add(new Question("Who is known for playing \"blonde bombshell\" characters in the 1950s?", "Marilyn Monroe", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Counting Cars");
        incorrects.set(1, "American Restoration");
        incorrects.set(2, "Storage Wars");
        incorrects.set(3, "The Bachelor");
        questions.add(new Question("\"Best I can do is 20$\" is a phrase from which reality show?", "Pawn Stars", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Leonard Hofstadter");
        incorrects.set(1, "Amy Farrah Fowler");
        incorrects.set(2, "Stuart Bloom");
        incorrects.set(3, "Bernadette Rostenkowski");
        questions.add(new Question("Which TBBT character is known for the phrase \"My mother got me tested.\"?", "Sheldon Cooper", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Efron and Hudgens");
        incorrects.set(1, "Lovato and Valderrama");
        incorrects.set(2, "Jolie and Pitt");
        incorrects.set(3, "Hawke and Thurman");
        questions.add(new Question("Which former celebrity couple is known for their matching denim outfit in 2001?", "Spears and Timberlake", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "D&G Light Blue");
        incorrects.set(1, "Guerlain Shalimar");
        incorrects.set(2, "Dior Diorissimo");
        incorrects.set(3, "Yves Saint Laurent Opium");
        questions.add(new Question("Which of the following is considered the most famous perfume ever?", "Chanel No. 5", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Flappy Bird");
        incorrects.set(1, "Fruit Ninja");
        incorrects.set(2, "Color Switch");
        incorrects.set(3, "Temple Run");
        questions.add(new Question("The use of which mobile game contributed to public accidents in the summer of 2016?", "Pokemon GO", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Nestle");
        incorrects.set(1, "PepsiCo");
        incorrects.set(2, "Powerade");
        incorrects.set(3, "Julius Meinl");
        questions.add(new Question("Which corporation is known for their Christmas truck commercials?", "The Coca-Cola Company", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Christiano Ronaldo");
        incorrects.set(1, "Lionel Messi");
        incorrects.set(2, "Usain Bolt");
        incorrects.set(3, "Serena Williams");
        questions.add(new Question("Which famous athlete is known for having two pairs of twins?", "Roger Federer", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Sylvester Stallone");
        incorrects.set(1, "Jean-Claude Van Damme");
        incorrects.set(2, "Dwayne Johnson");
        incorrects.set(3, "Tom Cruise");
        questions.add(new Question("Which actor and former body building champion was also the governor of California?", "Arnold Schwarzenegger", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Giselle Bundchen");
        incorrects.set(1, "Gigi Hadid");
        incorrects.set(2, "Adriana Lima");
        incorrects.set(3, "Liu Wen");
        questions.add(new Question("Who is the currently highest paid top model in the world?", "Kendall Jenner", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "The Simpsons");
        incorrects.set(1, "Futurama");
        incorrects.set(2, "Rick and Morty");
        incorrects.set(3, "Family Guy");
        questions.add(new Question("Which animated series follows the lives of four primary school boys?", "South Park", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Barack Obama");
        incorrects.set(1, "George W. Bush");
        incorrects.set(2, "Bill Clinton");
        incorrects.set(3, "Ronald Reagan");
        questions.add(new Question("Which American president is known for his controversial tweets?", "Donald Trump", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Spider-Man");
        incorrects.set(1, "Thor");
        incorrects.set(2, "Wolverine");
        incorrects.set(3, "Iron Man");
        questions.add(new Question("Which Marvel character is known for constantly breaking the 4th wall?", "Deadpool", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Baby");
        incorrects.set(1, "It’s Everday Bro");
        incorrects.set(2, "Friday");
        incorrects.set(3, "Anaconda");
        questions.add(new Question("As of June 2019, which is the most disliked video on YouTube?", "YouTube Rewind 2018", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "United Kingdom");
        incorrects.set(1, "Australia");
        incorrects.set(2, "USA");
        incorrects.set(3, "Canada");
        questions.add(new Question("Where was the Lord of the Rings trilogy filmed?", "New Zealand", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "The Philosophers Stone");
        incorrects.set(1, "The Prisoner of Azkaban");
        incorrects.set(2, "The Goblet of Fire");
        incorrects.set(3, "The Half-Blood Prince");
        questions.add(new Question("\"Dobby is a free elf!\" is a famous quote from which Harry Potter movie?", "The Chamber of Secrets", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Amish Paradise");
        incorrects.set(1, "Eat It");
        incorrects.set(2, "Like a Surgeon");
        incorrects.set(3, "Fat");
        questions.add(new Question("Which of the following is not a Weird Al Yankovic parody song?", "Call Me Maybe", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Ariana Grande");
        incorrects.set(1, "Rihanna");
        incorrects.set(2, "Lady GaGa");
        incorrects.set(3, "Usher");
        questions.add(new Question("Which of the following embarrassed themselves by not knowing what \"German\" means?", "Justin Bieber", incorrects));

        saveQuestionData(questions, "Pop Culture", context);
        questions.clear();



        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "June 13, 1944");
        incorrects.set(1, "July 21, 1943");
        incorrects.set(2, "May 7, 1943");
        incorrects.set(3, "July 2, 1944");
        questions.add(new Question("On which day did the Invasion of Normandy begin?", "June 6, 1944", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Neil Armstrong");
        incorrects.set(1, "Valentina Tereshkova");
        incorrects.set(2, "Buzz Aldrin");
        incorrects.set(3, "John Glenn");
        questions.add(new Question("What's the name of the first human in space?", "Yuri Alekseyevich Gagarin", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "March 15, 1775");
        incorrects.set(1, "September 9, 1774");
        incorrects.set(2, "June 12, 1776");
        incorrects.set(3, "July 6, 1776");
        questions.add(new Question("On which day did the USA gain independence?", "July 4, 1776", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "The Battle of Austerlitz");
        incorrects.set(1, "The Battle of Borodino");
        incorrects.set(2, "The Battle of Leipzig");
        incorrects.set(3, "The Battle of Jena-Auerstedt");
        questions.add(new Question("In which battle was Napoleon defeated?", "The Battle of Waterloo", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Italy");
        incorrects.set(1, "Egypt");
        incorrects.set(2, "Spain");
        incorrects.set(3, "Turkey");
        questions.add(new Question("In which today’s country was the Trojan horse used to win a war?", "Greece", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "German");
        incorrects.set(1, "Swiss");
        incorrects.set(2, "Hungarian");
        incorrects.set(3, "Polish");
        questions.add(new Question("What was Adolf Hitler's nationality?", "Austrian", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Maria Theresa");
        incorrects.set(1, "Louis XVI");
        incorrects.set(2, "Franz Joseph I");
        incorrects.set(3, "Louis XIV");
        questions.add(new Question("\"Let them eat cake!\" is a famous quote said by?", "Marie Antoinette", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "United States of America");
        incorrects.set(1, "Spain");
        incorrects.set(2, "The Netherlands");
        incorrects.set(3, "Germany");
        questions.add(new Question("Which country colonized Hong Kong?", "United Kingdom", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "1960");
        incorrects.set(1, "1953");
        incorrects.set(2, "1957");
        incorrects.set(3, "1968");
        questions.add(new Question("What year of the Cold War did the Cuban Missile Crisis happen?", "1962", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Mesha Stele");
        incorrects.set(1, "Admonitions Scroll");
        incorrects.set(2, "Voynich Manuscript");
        incorrects.set(3, "Kurkh Monoliths");
        questions.add(new Question("Which historical object holds the key to understanding Egyptian hieroglyphs?", "The Rosetta Stone", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "George Cayley");
        incorrects.set(1, "Otto Lillienthal");
        incorrects.set(2, "Gustave Whitehead");
        incorrects.set(3, "Richard Pearse");
        questions.add(new Question("Who invented the first successful airplane?", "The Wright brothers", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "5");
        incorrects.set(1, "6");
        incorrects.set(2, "3");
        incorrects.set(3, "2");
        questions.add(new Question("Into how many zones was Berlin divided after WWII?", "4", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "June 28, 1985");
        incorrects.set(1, "May 27, 1985");
        incorrects.set(2, "April 21, 1987");
        incorrects.set(3, "May 26, 1986");
        questions.add(new Question("When did the Chernobyl disaster happen?", "April 26, 1986", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Christopher Columbus");
        incorrects.set(1, "Vasco da Gama");
        incorrects.set(2, "Amerigo Vespucci");
        incorrects.set(3, "Ferdinand Magellan");
        questions.add(new Question("Who discovered Australia?", "James Cook", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Bhutan War");
        incorrects.set(1, "Anglo-Persian War");
        incorrects.set(2, "Dummer’s War");
        incorrects.set(3, "First Boer War");
        questions.add(new Question("Which was the shortest war in history?", "Anglo-Zanzibar War", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Henry Ford");
        incorrects.set(1, "Nikolaus Otto");
        incorrects.set(2, "Rudolf Diesel");
        incorrects.set(3, "Gottlieb Daimler");
        questions.add(new Question("Who invented cars?", "Karl Benz", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Lichtenstein");
        incorrects.set(1, "Switzerland");
        incorrects.set(2, "Albania");
        incorrects.set(3, "Moldova");
        questions.add(new Question("Which was the first country that gave women the right to vote?", "New Zealand", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "The White Plague");
        incorrects.set(1, "The Gray Death");
        incorrects.set(2, "The Dark Plague");
        incorrects.set(3, "The Blue Plague");
        questions.add(new Question("One of the most devastating pandemics of the 14th century is otherwise known as?", "The Black Death", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "474");
        incorrects.set(1, "356");
        incorrects.set(2, "398");
        incorrects.set(3, "405");
        questions.add(new Question("Which year marks the fall of the Western Roman Empire?", "476", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "CIA");
        incorrects.set(1, "OZNA");
        incorrects.set(2, "GPU");
        incorrects.set(3, "FSB");
        questions.add(new Question("What was the name of the intelligence agency in the former USSR?", "KGB", incorrects));

        saveQuestionData(questions, "History", context);
        questions.clear();




        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Naruto");
        incorrects.set(1, "Dragon Ball");
        incorrects.set(2, "Bleach");
        incorrects.set(3, "Sailor Moon");
        questions.add(new Question("Which is the most popular manga ever written?", "One Piece", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Lightning");
        incorrects.set(1, "Fire");
        incorrects.set(2, "Water");
        incorrects.set(3, "Earth");
        questions.add(new Question("What element does Naruto have the affinity for?", "Wind", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "French");
        incorrects.set(1, "Italian");
        incorrects.set(2, "English");
        incorrects.set(3, "Chinese");
        questions.add(new Question("What language is used in the intro of Attack on Titan?", "German", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Genos");
        incorrects.set(1, "Goku");
        incorrects.set(2, "Touma");
        incorrects.set(3, "Kazuma");
        questions.add(new Question("What is One Punch Man's name?", "Saitama", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Wendy Marvel");
        incorrects.set(1, "Laxus Dreyar");
        incorrects.set(2, "Sting Eucliffe");
        incorrects.set(3, "Gajeel Redfox");
        questions.add(new Question("Who of these is not a dragon slayer?", "Freed Justine", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Jolyne");
        incorrects.set(1, "Jonathan");
        incorrects.set(2, "Jotaro");
        incorrects.set(3, "Johnny");
        questions.add(new Question("What is the first name of Diamond is Unbreakable's JoJo?", "Josuke", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Rune magic");
        incorrects.set(1, "Reequipping magic");
        incorrects.set(2, "Light magic");
        incorrects.set(3, "Transformation magic");
        questions.add(new Question("What kind of magic does Gray Fullbuster use?", "Ice molding magic", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Time manipulation");
        incorrects.set(1, "Killing");
        incorrects.set(2, "Invisibility");
        incorrects.set(3, "Petrification");
        questions.add(new Question("What kind of power does Lelouch's Geass have?", "Mind control", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "A bar");
        incorrects.set(1, "A weapon");
        incorrects.set(2, "A person");
        incorrects.set(3, "A town");
        questions.add(new Question("What is Cowboy Bebop?", "A spaceship", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "N");
        incorrects.set(1, "D");
        incorrects.set(2, "M");
        incorrects.set(3, "E");
        questions.add(new Question("Who is Yagami Light \"Kira\"'s best friend and arch enemy?", "L", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Johto League");
        incorrects.set(1, "Kanto League");
        incorrects.set(2, "Sinnoh League");
        incorrects.set(3, "Hoenn League");
        questions.add(new Question("Which Pokemon League has Ash won", "Orange League", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Kamehameha");
        incorrects.set(1, "Garlick Gun");
        incorrects.set(2, "Tri-beam");
        incorrects.set(3, "Destructo Disc");
        questions.add(new Question("What is Picollo's finishing technique called?", "Special Beam Cannon", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Mori Kogoro");
        incorrects.set(1, "Akai Shuichi");
        incorrects.set(2, "Edogawa Conan");
        incorrects.set(3, "Hattori Heiji");
        questions.add(new Question("What is Detective Conan's real name?", "Kudo Shinichi", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Blonde");
        incorrects.set(1, "Brown");
        incorrects.set(2, "Green");
        incorrects.set(3, "Black");
        questions.add(new Question("What hair colour does the protagonist of Gintama have?", "White", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Usagi");
        incorrects.set(1, "Minako");
        incorrects.set(2, "Rei");
        incorrects.set(3, "Ami");
        questions.add(new Question("What is Sailor Jupiter's name?", "Makoto", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Envy");
        incorrects.set(1, "Gluttony");
        incorrects.set(2, "Pride");
        incorrects.set(3, "Lust");
        questions.add(new Question("Which sin does the protagonist of Seven Deadly Sins represent?", "Wrath", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Candy");
        incorrects.set(1, "Evil");
        incorrects.set(2, "Nature");
        incorrects.set(3, "Monsters");
        questions.add(new Question("After losing a game of chess, god transported Shiro and Sora to a world full of...?", "Games", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Super strength");
        incorrects.set(1, "Fire manipulation");
        incorrects.set(2, "Gravity altering");
        incorrects.set(3, "Electricity");
        questions.add(new Question("What power does Izuku Midoriya \"Deku\"'s rival have?", "Explosions", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "His Armour");
        incorrects.set(1, "His Horse");
        incorrects.set(2, "His Shield");
        incorrects.set(3, "His Army");
        questions.add(new Question("What is Berserk's Guts known for?", "His Sword", incorrects));

        incorrects = new ArrayList<String>(Arrays.asList(new String[4]));
        incorrects.set(0, "Demon");
        incorrects.set(1, "Werewolf");
        incorrects.set(2, "Angel");
        incorrects.set(3, "Shapeshifter");
        questions.add(new Question("What supernatural creature is Bakemonogatari's Araragi Koyomi?", "Vampire", incorrects));

        saveQuestionData(questions, "Anime", context);
        questions.clear();
    }

    public void saveQuestionData(ArrayList<Question> questionList, String fileName, Context context) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(questionList);
        objectOutputStream.close();
        fileOutputStream.close();
    }

}
