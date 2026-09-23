package com.example.data.model

data class QuizQuestion(
    val id: String,
    val category: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val hint: String,
    val graphicEmoji: String = "✨"
)

data class LevelData(
    val levelNumber: Int,
    val title: String,
    val subtitle: String,
    val iconEmoji: String,
    val unlockRewardCoins: Int = 50,
    val badgeUnlocked: String? = null,
    val questions: List<QuizQuestion>
)

data class BadgeDefinition(
    val id: String,
    val title: String,
    val description: String,
    val iconEmoji: String,
    val requiredStars: Int = 0,
    val requiredLevel: Int = 0
)

object QuizRepositoryData {
    val badges: List<BadgeDefinition> = listOf(
        BadgeDefinition(
            id = "badge_tiger_cub",
            title = "Tiger Cub Scout",
            description = "Completed Level 1: North India!",
            iconEmoji = "🐯",
            requiredLevel = 1
        ),
        BadgeDefinition(
            id = "badge_peninsula_pro",
            title = "Southern Explorer",
            description = "Completed Level 3: South India!",
            iconEmoji = "🥥",
            requiredLevel = 3
        ),
        BadgeDefinition(
            id = "badge_seven_sisters",
            title = "Mountain Champion",
            description = "Mastered the 7 Northeast Sisters in Level 5!",
            iconEmoji = "🏔️",
            requiredLevel = 5
        ),
        BadgeDefinition(
            id = "badge_island_voyager",
            title = "Island Voyager",
            description = "Conquered all Union Territories in Level 6!",
            iconEmoji = "🏝️",
            requiredLevel = 6
        ),
        BadgeDefinition(
            id = "badge_national_pride",
            title = "Tiranga Master",
            description = "Aced all National Symbols in Level 7!",
            iconEmoji = "🇮🇳",
            requiredLevel = 7
        ),
        BadgeDefinition(
            id = "badge_monument_guru",
            title = "Heritage Explorer",
            description = "Mastered India's famous monuments in Level 8!",
            iconEmoji = "🕌",
            requiredLevel = 8
        ),
        BadgeDefinition(
            id = "badge_festive_star",
            title = "Culture Superstar",
            description = "Celebrated India's festivals and dances in Level 9!",
            iconEmoji = "🪔",
            requiredLevel = 9
        ),
        BadgeDefinition(
            id = "badge_bharat_ratna_jr",
            title = "Bharat Grand Champion",
            description = "Completed the final Level 10 Grand Master Quiz!",
            iconEmoji = "👑",
            requiredLevel = 10
        ),
        BadgeDefinition(
            id = "badge_star_collector",
            title = "Galaxy of Stars",
            description = "Collected 20 or more golden stars across your journey!",
            iconEmoji = "⭐",
            requiredStars = 20
        )
    )

    val levels: List<LevelData> = listOf(
        // Level 1: North India Wonders
        LevelData(
            levelNumber = 1,
            title = "North India Frontiers",
            subtitle = "Discover northern states & capitals!",
            iconEmoji = "🏔️",
            unlockRewardCoins = 50,
            badgeUnlocked = "badge_tiger_cub",
            questions = listOf(
                QuizQuestion(
                    id = "l1_q1",
                    category = "State Capital",
                    question = "What is the capital city of Rajasthan, known as the 'Pink City'?",
                    options = listOf("Jaipur", "Udaipur", "Jodhpur", "Bikaner"),
                    correctIndex = 0,
                    explanation = "Jaipur is the capital of Rajasthan! It is called the 'Pink City' because of its beautiful terracotta pink colored buildings.",
                    hint = "Its name begins with the letter 'J' and it is home to Hawa Mahal!",
                    graphicEmoji = "🏰"
                ),
                QuizQuestion(
                    id = "l1_q2",
                    category = "State Capital",
                    question = "Which city is the shared capital for BOTH Punjab and Haryana?",
                    options = listOf("Amritsar", "Chandigarh", "Ludhiana", "Gurugram"),
                    correctIndex = 1,
                    explanation = "Chandigarh is a beautiful planned Union Territory that serves as the joint capital of both Punjab and Haryana!",
                    hint = "It is famously known as the 'City Beautiful' and has a famous Rock Garden.",
                    graphicEmoji = "🌾"
                ),
                QuizQuestion(
                    id = "l1_q3",
                    category = "State Capital",
                    question = "What is the summer capital of Himachal Pradesh, high up in the mountains?",
                    options = listOf("Manali", "Shimla", "Dharamshala", "Kullu"),
                    correctIndex = 1,
                    explanation = "Shimla is the capital of Himachal Pradesh! It is famous for its snowy pine hills and toy train.",
                    hint = "It starts with the letter 'S' and was once the summer capital of British India.",
                    graphicEmoji = "❄️"
                ),
                QuizQuestion(
                    id = "l1_q4",
                    category = "State Capital",
                    question = "What is the capital city of Uttar Pradesh, celebrated for its royal Awadhi kebabs and poetry?",
                    options = listOf("Agra", "Varanasi", "Lucknow", "Kanpur"),
                    correctIndex = 2,
                    explanation = "Lucknow is the capital of Uttar Pradesh! It is known as the 'City of Nawabs'.",
                    hint = "Begins with 'L' and is famous for Chikan embroidery and delicious food.",
                    graphicEmoji = "🕌"
                ),
                QuizQuestion(
                    id = "l1_q5",
                    category = "State Capital",
                    question = "What is the capital of the mountain state Uttarakhand, where holy River Ganga originates?",
                    options = listOf("Nainital", "Rishikesh", "Haridwar", "Dehradun"),
                    correctIndex = 3,
                    explanation = "Dehradun is the winter capital of Uttarakhand! It sits nestled between the Himalayas and Shivalik hills.",
                    hint = "Starts with 'Dehra...' in the Doon valley.",
                    graphicEmoji = "🌲"
                )
            )
        ),

        // Level 2: West & Central Jewels
        LevelData(
            levelNumber = 2,
            title = "West & Central Jewels",
            subtitle = "Journey through Maharashtra, Gujarat, Goa & MP!",
            iconEmoji = "🐅",
            unlockRewardCoins = 60,
            questions = listOf(
                QuizQuestion(
                    id = "l2_q1",
                    category = "State Capital",
                    question = "What is the capital city of Maharashtra, also known as the financial capital of India?",
                    options = listOf("Pune", "Mumbai", "Nagpur", "Nashik"),
                    correctIndex = 1,
                    explanation = "Mumbai is the vibrant capital of Maharashtra and the bustling hub of the Bollywood film industry!",
                    hint = "Home to the Gateway of India and Marine Drive!",
                    graphicEmoji = "🎬"
                ),
                QuizQuestion(
                    id = "l2_q2",
                    category = "State Capital",
                    question = "What is the capital of Gujarat, named after the Father of the Nation Mahatma Gandhi?",
                    options = listOf("Ahmedabad", "Surat", "Gandhinagar", "Vadodara"),
                    correctIndex = 2,
                    explanation = "Gandhinagar is the green capital of Gujarat, named in honor of Mahatma Gandhi!",
                    hint = "Look for Gandhi's name inside the city's title!",
                    graphicEmoji = "🦁"
                ),
                QuizQuestion(
                    id = "l2_q3",
                    category = "State Capital",
                    question = "What is the capital of Goa, India's sunny beach paradise?",
                    options = listOf("Margao", "Panaji", "Vasco da Gama", "Mapusa"),
                    correctIndex = 1,
                    explanation = "Panaji (or Panjim) is the charming capital of Goa, located along the banks of the Mandovi River!",
                    hint = "Starts with 'P' and has colorful Portuguese-style houses.",
                    graphicEmoji = "🏖️"
                ),
                QuizQuestion(
                    id = "l2_q4",
                    category = "State Capital",
                    question = "What is the capital of Madhya Pradesh, famously called the 'City of Lakes'?",
                    options = listOf("Bhopal", "Indore", "Gwalior", "Jabalpur"),
                    correctIndex = 0,
                    explanation = "Bhopal is the capital of Madhya Pradesh! It is famous for its scenic Upper and Lower lakes.",
                    hint = "Starts with 'Bho...'",
                    graphicEmoji = "⛵"
                ),
                QuizQuestion(
                    id = "l2_q5",
                    category = "State Capital",
                    question = "What is the capital city of Chhattisgarh, the 'Rice Bowl' of Central India?",
                    options = listOf("Bilaspur", "Raipur", "Durg", "Bhilai"),
                    correctIndex = 1,
                    explanation = "Raipur is the capital of Chhattisgarh! It is a major industrial hub with lush forests nearby.",
                    hint = "Rhymes with Jaipur, but starts with an 'R'!",
                    graphicEmoji = "🍚"
                )
            )
        ),

        // Level 3: Southern Peninsula
        LevelData(
            levelNumber = 3,
            title = "Southern Peninsula",
            subtitle = "Explore the sun-kissed states of South India!",
            iconEmoji = "🥥",
            unlockRewardCoins = 70,
            badgeUnlocked = "badge_peninsula_pro",
            questions = listOf(
                QuizQuestion(
                    id = "l3_q1",
                    category = "State Capital",
                    question = "What is the capital of Karnataka, hailed as the 'Silicon Valley of India'?",
                    options = listOf("Mysuru", "Bengaluru", "Mangaluru", "Hubballi"),
                    correctIndex = 1,
                    explanation = "Bengaluru (Bangalore) is the capital of Karnataka! It is world-famous for technology and its lush parks.",
                    hint = "Formerly known as Bangalore, home to ISRO headquarters!",
                    graphicEmoji = "💻"
                ),
                QuizQuestion(
                    id = "l3_q2",
                    category = "State Capital",
                    question = "What is the capital of Tamil Nadu, famous for Marina Beach and classical music?",
                    options = listOf("Madurai", "Coimbatore", "Chennai", "Salem"),
                    correctIndex = 2,
                    explanation = "Chennai is the capital of Tamil Nadu! Its Marina Beach is one of the longest urban beaches in the world.",
                    hint = "Previously known as Madras!",
                    graphicEmoji = "🛕"
                ),
                QuizQuestion(
                    id = "l3_q3",
                    category = "State Capital",
                    question = "What is the capital of Kerala, 'God's Own Country'?",
                    options = listOf("Kochi", "Kozhikode", "Thiruvananthapuram", "Thrissur"),
                    correctIndex = 2,
                    explanation = "Thiruvananthapuram (Trivandrum) is the green coastal capital of Kerala, home to the Padmanabhaswamy Temple!",
                    hint = "It is one of the longest city names in India, starting with 'Thiru...'!",
                    graphicEmoji = "🌴"
                ),
                QuizQuestion(
                    id = "l3_q4",
                    category = "State Capital",
                    question = "What is the capital of Telangana, celebrated for the iconic Charminar and Biryani?",
                    options = listOf("Warangal", "Hyderabad", "Nizamabad", "Karimnagar"),
                    correctIndex = 1,
                    explanation = "Hyderabad is the capital of Telangana! It is known as the 'City of Pearls'.",
                    hint = "Starts with 'Hyder...'!",
                    graphicEmoji = "💎"
                ),
                QuizQuestion(
                    id = "l3_q5",
                    category = "State Capital",
                    question = "What is the legislative capital of Andhra Pradesh, situated on the banks of Krishna River?",
                    options = listOf("Visakhapatnam", "Amaravati", "Vijayawada", "Tirupati"),
                    correctIndex = 1,
                    explanation = "Amaravati is the capital city of Andhra Pradesh, named after an ancient historic Buddhist center!",
                    hint = "Starts with 'Amara...'",
                    graphicEmoji = "🏛️"
                )
            )
        ),

        // Level 4: Eastern Kingdoms & Odisha
        LevelData(
            levelNumber = 4,
            title = "Eastern Kingdoms",
            subtitle = "Travel through Bihar, West Bengal, Odisha & Sikkim!",
            iconEmoji = "🐅",
            unlockRewardCoins = 80,
            questions = listOf(
                QuizQuestion(
                    id = "l4_q1",
                    category = "State Capital",
                    question = "What is the capital of West Bengal, home to the iconic Howrah Bridge?",
                    options = listOf("Darjeeling", "Siliguri", "Kolkata", "Asansol"),
                    correctIndex = 2,
                    explanation = "Kolkata is the cultural capital of West Bengal! It was earlier named Calcutta and is famous for trams and sweets.",
                    hint = "Famous for yellow ambassador taxis and Victoria Memorial!",
                    graphicEmoji = "🌉"
                ),
                QuizQuestion(
                    id = "l4_q2",
                    category = "State Capital",
                    question = "What is the capital city of Bihar, located on the southern bank of the River Ganga?",
                    options = listOf("Gaya", "Patna", "Muzaffarpur", "Bhagalpur"),
                    correctIndex = 1,
                    explanation = "Patna is the capital of Bihar! In ancient times, it was the magnificent imperial city of Pataliputra.",
                    hint = "Starts with 'P' and has just 5 letters!",
                    graphicEmoji = "📚"
                ),
                QuizQuestion(
                    id = "l4_q3",
                    category = "State Capital",
                    question = "What is the capital of Odisha, famously known as the 'Temple City of India'?",
                    options = listOf("Puri", "Cuttack", "Bhubaneswar", "Rourkela"),
                    correctIndex = 2,
                    explanation = "Bhubaneswar is the capital of Odisha! It has hundreds of ancient stone temples built over centuries.",
                    hint = "Starts with 'Bhuban...'!",
                    graphicEmoji = "🛕"
                ),
                QuizQuestion(
                    id = "l4_q4",
                    category = "State Capital",
                    question = "What is the capital city of Jharkhand, the city where cricket legend MS Dhoni grew up?",
                    options = listOf("Jamshedpur", "Dhanbad", "Ranchi", "Bokaro"),
                    correctIndex = 2,
                    explanation = "Ranchi is the capital of Jharkhand! It is surrounded by beautiful waterfalls like Hundru and Jonha.",
                    hint = "Starts with 'R' and has 6 letters.",
                    graphicEmoji = "🏏"
                ),
                QuizQuestion(
                    id = "l4_q5",
                    category = "State Capital",
                    question = "What is the capital of Sikkim, the mountainous state gazing at Mount Kangchenjunga?",
                    options = listOf("Gangtok", "Pelling", "Namchi", "Ravangla"),
                    correctIndex = 0,
                    explanation = "Gangtok is the peaceful capital of Sikkim, known for its Buddhist monasteries, cable cars, and clean streets.",
                    hint = "Starts with 'Gang...'!",
                    graphicEmoji = "🏔️"
                )
            )
        ),

        // Level 5: Seven Sisters of Northeast
        LevelData(
            levelNumber = 5,
            title = "Seven Sisters of Northeast",
            subtitle = "Discover the magical northeastern states!",
            iconEmoji = "🦏",
            unlockRewardCoins = 90,
            badgeUnlocked = "badge_seven_sisters",
            questions = listOf(
                QuizQuestion(
                    id = "l5_q1",
                    category = "State Capital",
                    question = "What is the capital of Assam, world-renowned for tea gardens and one-horned rhinos?",
                    options = listOf("Guwahati", "Dispur", "Jorhat", "Dibrugarh"),
                    correctIndex = 1,
                    explanation = "Dispur is the official capital of Assam! (Guwahati is the largest city, and Dispur sits right inside it).",
                    hint = "Starts with 'D' and rhymes with 'Pur'!",
                    graphicEmoji = "🦏"
                ),
                QuizQuestion(
                    id = "l5_q2",
                    category = "State Capital",
                    question = "What is the capital of Arunachal Pradesh, where the sun rises first in India?",
                    options = listOf("Tawang", "Itanagar", "Ziro", "Pasighat"),
                    correctIndex = 1,
                    explanation = "Itanagar is the capital of Arunachal Pradesh! It is home to the historic Ita Fort built of red bricks.",
                    hint = "Starts with 'Ita...'!",
                    graphicEmoji = "🌄"
                ),
                QuizQuestion(
                    id = "l5_q3",
                    category = "State Capital",
                    question = "What is the capital of Meghalaya, known as the 'Scotland of the East'?",
                    options = listOf("Shillong", "Cherrapunji", "Tura", "Jowai"),
                    correctIndex = 0,
                    explanation = "Shillong is the picturesque capital of Meghalaya, famous for pine hills, waterfalls, and music lovers!",
                    hint = "Starts with 'Shill...'!",
                    graphicEmoji = "🌧️"
                ),
                QuizQuestion(
                    id = "l5_q4",
                    category = "State Capital",
                    question = "What is the capital of Manipur, famous for classical dance and Loktak floating lake?",
                    options = listOf("Imphal", "Churachandpur", "Thoubal", "Ukhrul"),
                    correctIndex = 0,
                    explanation = "Imphal is the capital of Manipur! It has Kangla Fort and the world's oldest living polo ground.",
                    hint = "Starts with 'Im...'!",
                    graphicEmoji = "🦌"
                ),
                QuizQuestion(
                    id = "l5_q5",
                    category = "State Capital",
                    question = "What is the capital of Nagaland, which hosts the famous Hornbill Festival?",
                    options = listOf("Dimapur", "Kohima", "Mokokchung", "Mon"),
                    correctIndex = 1,
                    explanation = "Kohima is the hilly capital of Nagaland, famous for war memorial gardens and vibrant tribal culture.",
                    hint = "Starts with 'Ko...'!",
                    graphicEmoji = "🪶"
                ),
                QuizQuestion(
                    id = "l5_q6",
                    category = "State Capital",
                    question = "What is the capital of Mizoram, celebrated for the bamboo dance (Cheraw)?",
                    options = listOf("Lunglei", "Champhai", "Aizawl", "Serchhip"),
                    correctIndex = 2,
                    explanation = "Aizawl is the scenic ridge-top capital of Mizoram! Its houses cling dramatically to green mountain ridges.",
                    hint = "Pronounced 'Eye-zawl'!",
                    graphicEmoji = "🎋"
                ),
                QuizQuestion(
                    id = "l5_q7",
                    category = "State Capital",
                    question = "What is the capital of Tripura, home to the royal Ujjayanta Palace?",
                    options = listOf("Agartala", "Udaipur", "Dharmanagar", "Kailashahar"),
                    correctIndex = 0,
                    explanation = "Agartala is the capital of Tripura! The magnificent Ujjayanta Palace was built by Maharaja Radha Kishore Manikya.",
                    hint = "Starts with 'Agar...'!",
                    graphicEmoji = "👑"
                )
            )
        ),

        // Level 6: Islands & Union Territories
        LevelData(
            levelNumber = 6,
            title = "Islands & Union Territories",
            subtitle = "Master all 8 Union Territories of India!",
            iconEmoji = "🏝️",
            unlockRewardCoins = 100,
            badgeUnlocked = "badge_island_voyager",
            questions = listOf(
                QuizQuestion(
                    id = "l6_q1",
                    category = "Union Territory",
                    question = "How many Union Territories does the country of India have?",
                    options = listOf("6", "7", "8", "9"),
                    correctIndex = 2,
                    explanation = "India has exactly 8 Union Territories and 28 States!",
                    hint = "Two more than 6, and one less than 9!",
                    graphicEmoji = "🗺️"
                ),
                QuizQuestion(
                    id = "l6_q2",
                    category = "Union Territory",
                    question = "What is the capital of the Andaman and Nicobar Islands in the Bay of Bengal?",
                    options = listOf("Havelock", "Port Blair", "Car Nicobar", "Neil Island"),
                    correctIndex = 1,
                    explanation = "Port Blair is the capital city of Andaman and Nicobar Islands, where the historic Cellular Jail stands.",
                    hint = "Named after British officer Archibald Blair: Port ...",
                    graphicEmoji = "🏝️"
                ),
                QuizQuestion(
                    id = "l6_q3",
                    category = "Union Territory",
                    question = "What is the capital of Ladakh, the high-altitude 'Land of Passes'?",
                    options = listOf("Kargil", "Leh", "Nubra", "Drass"),
                    correctIndex = 1,
                    explanation = "Leh is the capital of Ladakh, situated at an altitude of over 3,500 meters amidst dramatic mountains!",
                    hint = "Has only 3 letters: L-e-h!",
                    graphicEmoji = "🏔️"
                ),
                QuizQuestion(
                    id = "l6_q4",
                    category = "Union Territory",
                    question = "What is the capital of Lakshadweep, the coral island paradise in the Arabian Sea?",
                    options = listOf("Agatti", "Minicoy", "Kavaratti", "Andrott"),
                    correctIndex = 2,
                    explanation = "Kavaratti is the capital of Lakshadweep, surrounded by calm turquoise lagoons and sea turtles!",
                    hint = "Starts with 'Kava...'!",
                    graphicEmoji = "🐠"
                ),
                QuizQuestion(
                    id = "l6_q5",
                    category = "Union Territory",
                    question = "What is the summer capital of the Union Territory of Jammu and Kashmir?",
                    options = listOf("Jammu", "Srinagar", "Anantnag", "Baramulla"),
                    correctIndex = 1,
                    explanation = "Srinagar is the summer capital (famous for Dal Lake houseboats), while Jammu is the winter capital!",
                    hint = "Famous for floating Shikara boats on Dal Lake!",
                    graphicEmoji = "❄️"
                ),
                QuizQuestion(
                    id = "l6_q6",
                    category = "Union Territory",
                    question = "What is the administrative capital of Dadra and Nagar Haveli and Daman and Diu?",
                    options = listOf("Silvassa", "Diu", "Daman", "Vapi"),
                    correctIndex = 2,
                    explanation = "Daman is the official capital of the merged Union Territory of Dadra & Nagar Haveli and Daman & Diu!",
                    hint = "Starts with 'Dam...'!",
                    graphicEmoji = "⛵"
                )
            )
        ),

        // Level 7: National Symbols & Tricolor
        LevelData(
            levelNumber = 7,
            title = "National Symbols & Tricolor",
            subtitle = "Celebrate the pride of India: animal, bird, flower & flag!",
            iconEmoji = "🇮🇳",
            unlockRewardCoins = 100,
            badgeUnlocked = "badge_national_pride",
            questions = listOf(
                QuizQuestion(
                    id = "l7_q1",
                    category = "National Symbol",
                    question = "What is the National Animal of India?",
                    options = listOf("Asiatic Lion", "Royal Bengal Tiger", "Indian Elephant", "Indian Rhinoceros"),
                    correctIndex = 1,
                    explanation = "The Royal Bengal Tiger is India's National Animal, symbolizing grace, strength, agility, and power!",
                    hint = "It has orange fur with black stripes and a fierce roar!",
                    graphicEmoji = "🐅"
                ),
                QuizQuestion(
                    id = "l7_q2",
                    category = "National Symbol",
                    question = "What is the National Bird of India with magnificent shimmering feathers?",
                    options = listOf("Kingfisher", "Indian Peacock", "Hornbill", "Parrot"),
                    correctIndex = 1,
                    explanation = "The Indian Peacock (Mayura) is India's National Bird, known for spreading its iridescent blue-green feathers in rain dances!",
                    hint = "It makes a joyful call when it rains and spreads its fan of feathers.",
                    graphicEmoji = "🦚"
                ),
                QuizQuestion(
                    id = "l7_q3",
                    category = "National Symbol",
                    question = "Which sacred water plant is the National Flower of India?",
                    options = listOf("Rose", "Marigold", "Lotus", "Jasmine"),
                    correctIndex = 2,
                    explanation = "The Lotus (Kamal) is the National Flower of India, symbolizing purity, beauty, and wisdom.",
                    hint = "It blossoms cleanly above muddy waters with pink or white petals.",
                    graphicEmoji = "🪷"
                ),
                QuizQuestion(
                    id = "l7_q4",
                    category = "National Flag",
                    question = "How many spokes are there in the Ashoka Chakra on the Indian Tricolor flag?",
                    options = listOf("12", "18", "24", "32"),
                    correctIndex = 2,
                    explanation = "The Ashoka Chakra in the center of the Indian flag has 24 navy-blue spokes representing the 24 hours of the day and righteous virtue!",
                    hint = "Exactly the number of hours in a single day!",
                    graphicEmoji = "☸️"
                ),
                QuizQuestion(
                    id = "l7_q5",
                    category = "National Anthem",
                    question = "Who composed India's National Anthem, 'Jana Gana Mana'?",
                    options = listOf("Bankim Chandra Chatterjee", "Rabindranath Tagore", "Sarojini Naidu", "Swami Vivekananda"),
                    correctIndex = 1,
                    explanation = "Gurudev Rabindranath Tagore composed 'Jana Gana Mana'! He was also the first Asian to win the Nobel Prize in Literature.",
                    hint = "The Nobel Laureate poet from Bengal!",
                    graphicEmoji = "🎵"
                ),
                QuizQuestion(
                    id = "l7_q6",
                    category = "National Symbol",
                    question = "What is the National Fruit of India, loved in summer by all kids?",
                    options = listOf("Apple", "Mango", "Banana", "Guava"),
                    correctIndex = 1,
                    explanation = "The sweet and juicy Mango is the National Fruit of India, celebrated as the 'King of Fruits'!",
                    hint = "Alphonso and Dasheri are famous varieties!",
                    graphicEmoji = "🥭"
                ),
                QuizQuestion(
                    id = "l7_q7",
                    category = "National Symbol",
                    question = "What is the National Tree of India, with branches that send down hanging roots?",
                    options = listOf("Neem Tree", "Banyan Tree", "Peepal Tree", "Mango Tree"),
                    correctIndex = 1,
                    explanation = "The Indian Banyan Tree (Bargad) is the National Tree of India, symbolizing eternal life and shelter for travelers.",
                    hint = "Famous for aerial roots hanging down from huge branches!",
                    graphicEmoji = "🌳"
                )
            )
        ),

        // Level 8: Famous Monuments & Wonders
        LevelData(
            levelNumber = 8,
            title = "Wonders & Monuments",
            subtitle = "Explore the grand architectural treasures of India!",
            iconEmoji = "🕌",
            unlockRewardCoins = 110,
            badgeUnlocked = "badge_monument_guru",
            questions = listOf(
                QuizQuestion(
                    id = "l8_q1",
                    category = "Monuments",
                    question = "In which historic city is the world wonder, the Taj Mahal, located?",
                    options = listOf("Delhi", "Agra", "Jaipur", "Lucknow"),
                    correctIndex = 1,
                    explanation = "The Taj Mahal is in Agra, Uttar Pradesh! It was built by Mughal Emperor Shah Jahan in memory of Mumtaz Mahal.",
                    hint = "Home to the famous Agra Petha sweets!",
                    graphicEmoji = "🕌"
                ),
                QuizQuestion(
                    id = "l8_q2",
                    category = "Monuments",
                    question = "Where is the famous 'Gateway of India' located, overlooking the Arabian Sea?",
                    options = listOf("Kolkata", "Chennai", "Mumbai", "Kochi"),
                    correctIndex = 2,
                    explanation = "The Gateway of India is located at Apollo Bunder in Mumbai, Maharashtra!",
                    hint = "Located in the City of Dreams, near the Taj Mahal Palace Hotel.",
                    graphicEmoji = "⛵"
                ),
                QuizQuestion(
                    id = "l8_q3",
                    category = "Monuments",
                    question = "Where is the India Gate war memorial located?",
                    options = listOf("New Delhi", "Mumbai", "Amritsar", "Chandigarh"),
                    correctIndex = 0,
                    explanation = "India Gate is a majestic 42-meter high triumphal arch in New Delhi commemorating brave soldiers!",
                    hint = "In India's national capital!",
                    graphicEmoji = "🏛️"
                ),
                QuizQuestion(
                    id = "l8_q4",
                    category = "Monuments",
                    question = "Which monument in Jaipur is famous as the 'Palace of Winds' with 953 small windows?",
                    options = listOf("Amber Fort", "Hawa Mahal", "City Palace", "Jantar Mantar"),
                    correctIndex = 1,
                    explanation = "Hawa Mahal (Palace of Winds) in Jaipur has 953 intricate jharokhas (casements) that let cool breezes flow through!",
                    hint = "'Hawa' means wind/air in Hindi!",
                    graphicEmoji = "🌬️"
                ),
                QuizQuestion(
                    id = "l8_q5",
                    category = "Monuments",
                    question = "Where is the magnificent Golden Temple (Harmandir Sahib) located?",
                    options = listOf("Ludhiana", "Patiala", "Amritsar", "Jalandhar"),
                    correctIndex = 2,
                    explanation = "The Golden Temple is in Amritsar, Punjab! It serves free meals (langar) to over 100,000 visitors every day regardless of background.",
                    hint = "Starts with 'Amrit...' meaning nectar of immortality!",
                    graphicEmoji = "✨"
                ),
                QuizQuestion(
                    id = "l8_q6",
                    category = "Monuments",
                    question = "Which towering monument with four grand minarets was built in Hyderabad in 1591?",
                    options = listOf("Golconda Fort", "Charminar", "Qutub Shahi Tombs", "Falaknuma Palace"),
                    correctIndex = 1,
                    explanation = "Charminar (Four Minarets) is the iconic landmark of Hyderabad, built by Sultan Muhammad Quli Qutb Shah!",
                    hint = "'Char' means four and 'Minar' means tower!",
                    graphicEmoji = "💎"
                )
            )
        ),

        // Level 9: Festivals, Dances & Delights
        LevelData(
            levelNumber = 9,
            title = "Festivals, Dances & Foods",
            subtitle = "Celebrate colorful cultural traditions across India!",
            iconEmoji = "🪔",
            unlockRewardCoins = 120,
            badgeUnlocked = "badge_festive_star",
            questions = listOf(
                QuizQuestion(
                    id = "l9_q1",
                    category = "Festivals",
                    question = "Which festival is celebrated across India as the 'Festival of Lights' with clay diyas and sweets?",
                    options = listOf("Holi", "Diwali", "Eid", "Navratri"),
                    correctIndex = 1,
                    explanation = "Diwali (Deepavali) is the joyful festival of lights celebrating the victory of light over darkness and good over evil!",
                    hint = "People light diyas and burst sparkling phuljharis!",
                    graphicEmoji = "🪔"
                ),
                QuizQuestion(
                    id = "l9_q2",
                    category = "Dance",
                    question = "Which energetic folk dance originated in Punjab, performed to the lively beat of the Dhol drum?",
                    options = listOf("Garba", "Kathak", "Bhangra", "Ghoomar"),
                    correctIndex = 2,
                    explanation = "Bhangra is a lively folk dance of Punjab traditionally danced during Baisakhi harvest festivals!",
                    hint = "Dancers jump with arms raised shouting 'Balle Balle'!",
                    graphicEmoji = "🥁"
                ),
                QuizQuestion(
                    id = "l9_q3",
                    category = "Festivals",
                    question = "Which festival of colors welcomes the spring season with vibrant gulal powder and water splashes?",
                    options = listOf("Holi", "Dussehra", "Makar Sankranti", "Raksha Bandhan"),
                    correctIndex = 0,
                    explanation = "Holi is the exuberant festival of colors where family and friends throw colorful powders and share Gujiya sweets!",
                    hint = "People happily say 'Bura na mano, Holi hai'!",
                    graphicEmoji = "🎨"
                ),
                QuizQuestion(
                    id = "l9_q4",
                    category = "Dance",
                    question = "In which colorful circle dance from Gujarat do participants click decorated sticks together during Navratri?",
                    options = listOf("Dandiya Raas", "Lavani", "Bihu", "Chhau"),
                    correctIndex = 0,
                    explanation = "Dandiya Raas is the traditional folk dance of Gujarat performed with polished wooden sticks during the 9 nights of Navratri!",
                    hint = "The wooden sticks are called Dandiyas!",
                    graphicEmoji = "🥢"
                ),
                QuizQuestion(
                    id = "l9_q5",
                    category = "Dance",
                    question = "Which classical dance drama from Kerala features elaborate colorful face paint and large headgear?",
                    options = listOf("Kathakali", "Mohiniyattam", "Kuchipudi", "Odissi"),
                    correctIndex = 0,
                    explanation = "Kathakali is the story-play dance of Kerala where actors wear vivid green face makeup, billowing skirts, and crown headpieces!",
                    hint = "'Katha' means story and 'Kali' means play!",
                    graphicEmoji = "🎭"
                ),
                QuizQuestion(
                    id = "l9_q6",
                    category = "Festivals",
                    question = "Which harvest festival in Tamil Nadu is celebrated by boiling sweet rice in clay pots until it overflows?",
                    options = listOf("Pongal", "Onam", "Ugadi", "Vishu"),
                    correctIndex = 0,
                    explanation = "Pongal is Tamil Nadu's four-day harvest thanksgiving festival! People shout 'Pongalo Pongal' as milk overflows.",
                    hint = "Starts with 'P' and shares the name of a sweet jaggery rice dish!",
                    graphicEmoji = "🍲"
                )
            )
        ),

        // Level 10: Grand Bharat Master Quiz
        LevelData(
            levelNumber = 10,
            title = "Grand Bharat Master Quiz",
            subtitle = "The ultimate challenge to earn the Bharat Grand Champion crown!",
            iconEmoji = "👑",
            unlockRewardCoins = 200,
            badgeUnlocked = "badge_bharat_ratna_jr",
            questions = listOf(
                QuizQuestion(
                    id = "l10_q1",
                    category = "General Knowledge",
                    question = "Who is affectionately known as the 'Father of the Nation' in India?",
                    options = listOf("Jawaharlal Nehru", "Subhas Chandra Bose", "Mahatma Gandhi", "Sardar Vallabhbhai Patel"),
                    correctIndex = 2,
                    explanation = "Mahatma Gandhi (Mohandas Karamchand Gandhi) is revered as the Father of the Nation (Bapu) for leading India's peaceful independence movement!",
                    hint = "His birthday is celebrated on October 2nd as Gandhi Jayanti.",
                    graphicEmoji = "👓"
                ),
                QuizQuestion(
                    id = "l10_q2",
                    category = "General Knowledge",
                    question = "Which ancient Indian mathematician and astronomer invented the concept of 'Zero' (Shunya)?",
                    options = listOf("Aryabhata", "Varahamihira", "Brahmagupta", "Bhaskara"),
                    correctIndex = 0,
                    explanation = "Aryabhata was the brilliant ancient Indian mathematician whose work introduced the place-value system and zero to the world!",
                    hint = "India's first satellite sent to space was named after him.",
                    graphicEmoji = "0️⃣"
                ),
                QuizQuestion(
                    id = "l10_q3",
                    category = "General Knowledge",
                    question = "Which beloved President of India was known as the 'Missile Man of India' and loved inspiring students?",
                    options = listOf("Dr. Rajendra Prasad", "Dr. A.P.J. Abdul Kalam", "Dr. S. Radhakrishnan", "Pranab Mukherjee"),
                    correctIndex = 1,
                    explanation = "Dr. APJ Abdul Kalam was a visionary aerospace scientist and India's 11th President who wrote 'Wings of Fire'!",
                    hint = "Dr. Kalam loved speaking with children across India!",
                    graphicEmoji = "🚀"
                ),
                QuizQuestion(
                    id = "l10_q4",
                    category = "Geography",
                    question = "Which is the highest mountain peak situated inside India (in Sikkim)?",
                    options = listOf("Kangchenjunga", "Nanda Devi", "Mount Everest", "K2"),
                    correctIndex = 0,
                    explanation = "Kangchenjunga (8,586 m) in Sikkim is the highest peak in India and the third highest mountain in the world!",
                    hint = "Located in Sikkim, visible from Gangtok and Darjeeling.",
                    graphicEmoji = "🏔️"
                ),
                QuizQuestion(
                    id = "l10_q5",
                    category = "General Knowledge",
                    question = "Who was the first Indian woman astronaut to travel into space?",
                    options = listOf("Sunita Williams", "Kalpana Chawla", "Tessy Thomas", "Rakesh Sharma"),
                    correctIndex = 1,
                    explanation = "Kalpana Chawla was born in Karnal, Haryana, and became the first woman of Indian origin to fly into space aboard space shuttle Columbia!",
                    hint = "Her first name means 'Imagination' in Hindi!",
                    graphicEmoji = "⭐"
                ),
                QuizQuestion(
                    id = "l10_q6",
                    category = "National Symbol",
                    question = "What is the National Heritage Animal of India, symbolizing wisdom and majestic heritage?",
                    options = listOf("Indian Elephant", "Leopard", "Lion", "Camel"),
                    correctIndex = 0,
                    explanation = "The Indian Elephant was declared the National Heritage Animal of India in 2010 to promote its protection and conservation.",
                    hint = "The gentle giant with a long trunk and big ears!",
                    graphicEmoji = "🐘"
                ),
                QuizQuestion(
                    id = "l10_q7",
                    category = "General Knowledge",
                    question = "What is the official currency of India?",
                    options = listOf("Dollar", "Rupee", "Dinar", "Yen"),
                    correctIndex = 1,
                    explanation = "The Indian Rupee (₹) is the official currency! The unique ₹ currency symbol was designed by Indian designer D. Udaya Kumar.",
                    hint = "Symbolized by ₹!",
                    graphicEmoji = "🪙"
                )
            )
        )
    )
}
