package com.example.data.model

enum class TerritoryType {
    STATE,
    UNION_TERRITORY
}

enum class IndiaZone(val displayName: String) {
    NORTH("North India"),
    SOUTH("South India"),
    EAST("East India"),
    WEST("West India"),
    CENTRAL("Central India"),
    NORTHEAST("North-East India")
}

data class IndiaState(
    val id: String,
    val name: String,
    val capital: String,
    val type: TerritoryType,
    val zone: IndiaZone,
    val primaryLanguage: String,
    val famousLandmark: String,
    val funFact: String,
    val emojiIcon: String
)

object IndiaDataStore {
    val allTerritories: List<IndiaState> = listOf(
        // North India
        IndiaState(
            id = "punjab",
            name = "Punjab",
            capital = "Chandigarh",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Punjabi",
            famousLandmark = "Golden Temple (Harmandir Sahib)",
            funFact = "Punjab means 'Land of Five Rivers'! It is famous for Bhangra dance and delicious Makki di Roti with Sarson da Saag.",
            emojiIcon = "🌾"
        ),
        IndiaState(
            id = "haryana",
            name = "Haryana",
            capital = "Chandigarh",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Hindi & Haryanvi",
            famousLandmark = "Kurukshetra & Sultanpur Bird Sanctuary",
            funFact = "Haryana shares its capital city, Chandigarh, with its neighbor Punjab! It is known as the 'Milk Pail of India'.",
            emojiIcon = "🥛"
        ),
        IndiaState(
            id = "himachal_pradesh",
            name = "Himachal Pradesh",
            capital = "Shimla",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Hindi & Pahari",
            famousLandmark = "Rohtang Pass & Spiti Valley",
            funFact = "Known as 'Dev Bhoomi' (Land of the Gods) and the 'Apple State' because it grows sweet, crunchy apples!",
            emojiIcon = "🍎"
        ),
        IndiaState(
            id = "uttarakhand",
            name = "Uttarakhand",
            capital = "Dehradun",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Hindi & Garhwali",
            famousLandmark = "Jim Corbett National Park & Valley of Flowers",
            funFact = "The holy River Ganga begins its journey in Uttarakhand from the Gangotri glacier!",
            emojiIcon = "🏔️"
        ),
        IndiaState(
            id = "uttar_pradesh",
            name = "Uttar Pradesh",
            capital = "Lucknow",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Hindi & Urdu",
            famousLandmark = "Taj Mahal in Agra & Varanasi Ghats",
            funFact = "Uttar Pradesh has the world-famous wonder, the Taj Mahal, built out of sparkling white marble!",
            emojiIcon = "🕌"
        ),
        IndiaState(
            id = "rajasthan",
            name = "Rajasthan",
            capital = "Jaipur",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Hindi & Rajasthani",
            famousLandmark = "Hawa Mahal & Amber Fort",
            funFact = "Rajasthan is the largest state in India by land area! Its capital, Jaipur, is famously called the 'Pink City'.",
            emojiIcon = "🏰"
        ),

        // Central India
        IndiaState(
            id = "madhya_pradesh",
            name = "Madhya Pradesh",
            capital = "Bhopal",
            type = TerritoryType.STATE,
            zone = IndiaZone.CENTRAL,
            primaryLanguage = "Hindi",
            famousLandmark = "Khajuraho Temples & Sanchi Stupa",
            funFact = "Called the 'Heart of India' because it sits right in the middle, and also the 'Tiger State' because it has so many wild tigers!",
            emojiIcon = "🐅"
        ),
        IndiaState(
            id = "chhattisgarh",
            name = "Chhattisgarh",
            capital = "Raipur",
            type = TerritoryType.STATE,
            zone = IndiaZone.CENTRAL,
            primaryLanguage = "Chhattisgarhi & Hindi",
            famousLandmark = "Chitrakote Waterfalls (India's Niagara)",
            funFact = "Chhattisgarh is called the 'Rice Bowl of Central India' because farmers grow more than 20,000 varieties of rice here!",
            emojiIcon = "🍚"
        ),

        // West India
        IndiaState(
            id = "gujarat",
            name = "Gujarat",
            capital = "Gandhinagar",
            type = TerritoryType.STATE,
            zone = IndiaZone.WEST,
            primaryLanguage = "Gujarati",
            famousLandmark = "Statue of Unity (World's Tallest) & Gir Forest",
            funFact = "Gir National Park in Gujarat is the only natural home of the majestic Asiatic Lion in the entire world!",
            emojiIcon = "🦁"
        ),
        IndiaState(
            id = "maharashtra",
            name = "Maharashtra",
            capital = "Mumbai",
            type = TerritoryType.STATE,
            zone = IndiaZone.WEST,
            primaryLanguage = "Marathi",
            famousLandmark = "Gateway of India & Ajanta-Ellora Caves",
            funFact = "Mumbai is famous as the home of Bollywood films! Maharashtra is also loved for delicious Vada Pav and Alphonso mangoes.",
            emojiIcon = "🎬"
        ),
        IndiaState(
            id = "goa",
            name = "Goa",
            capital = "Panaji",
            type = TerritoryType.STATE,
            zone = IndiaZone.WEST,
            primaryLanguage = "Konkani",
            famousLandmark = "Basilica of Bom Jesus & Calangute Beach",
            funFact = "Goa is the smallest state in India by area, but it has over 100 kilometers of sunny golden beaches!",
            emojiIcon = "🏖️"
        ),

        // South India
        IndiaState(
            id = "karnataka",
            name = "Karnataka",
            capital = "Bengaluru",
            type = TerritoryType.STATE,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Kannada",
            famousLandmark = "Mysore Palace & Hampi Ruins",
            funFact = "Its capital Bengaluru is called the 'Silicon Valley of India' and the 'Garden City' for its beautiful tree-lined parks.",
            emojiIcon = "💻"
        ),
        IndiaState(
            id = "kerala",
            name = "Kerala",
            capital = "Thiruvananthapuram",
            type = TerritoryType.STATE,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Malayalam",
            famousLandmark = "Alleppey Backwaters & Munnar Tea Hills",
            funFact = "Nicknamed 'God's Own Country' with scenic coconut palms, houseboats, and the vibrant Kathakali masked dance.",
            emojiIcon = "🥥"
        ),
        IndiaState(
            id = "tamil_nadu",
            name = "Tamil Nadu",
            capital = "Chennai",
            type = TerritoryType.STATE,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Tamil",
            famousLandmark = "Brihadeeswarar Temple & Meenakshi Temple",
            funFact = "Tamil is one of the oldest classical languages in the world! Tamil Nadu is celebrated for the classical dance Bharatanatyam.",
            emojiIcon = "🛕"
        ),
        IndiaState(
            id = "andhra_pradesh",
            name = "Andhra Pradesh",
            capital = "Amaravati",
            type = TerritoryType.STATE,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Telugu",
            famousLandmark = "Tirupati Balaji Temple & Borra Caves",
            funFact = "Known as the 'Egg Bowl of Asia' and famous worldwide for the classical Kuchipudi dance and mouth-watering spicy curries.",
            emojiIcon = "🍛"
        ),
        IndiaState(
            id = "telangana",
            name = "Telangana",
            capital = "Hyderabad",
            type = TerritoryType.STATE,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Telugu & Urdu",
            famousLandmark = "Charminar & Golconda Fort",
            funFact = "Hyderabad is called the 'City of Pearls' and is famous across the globe for authentic Hyderabadi Dum Biryani!",
            emojiIcon = "💎"
        ),

        // East India
        IndiaState(
            id = "bihar",
            name = "Bihar",
            capital = "Patna",
            type = TerritoryType.STATE,
            zone = IndiaZone.EAST,
            primaryLanguage = "Hindi & Maithili",
            famousLandmark = "Mahabodhi Temple & Nalanda Ancient University",
            funFact = "Nalanda in Bihar was one of the world's very first residential universities where scholars from across Asia came to study!",
            emojiIcon = "📚"
        ),
        IndiaState(
            id = "jharkhand",
            name = "Jharkhand",
            capital = "Ranchi",
            type = TerritoryType.STATE,
            zone = IndiaZone.EAST,
            primaryLanguage = "Hindi",
            famousLandmark = "Betla National Park & Hundru Falls",
            funFact = "Jharkhand means 'Land of Forests'. It is rich in minerals and is the hometown of cricket legend MS Dhoni!",
            emojiIcon = "🏏"
        ),
        IndiaState(
            id = "west_bengal",
            name = "West Bengal",
            capital = "Kolkata",
            type = TerritoryType.STATE,
            zone = IndiaZone.EAST,
            primaryLanguage = "Bengali",
            famousLandmark = "Victoria Memorial & Howrah Bridge",
            funFact = "Home to the Sundarbans mangrove forest with Royal Bengal Tigers, and sweet desserts like spongy Rasgulla and Sandesh!",
            emojiIcon = "🍬"
        ),
        IndiaState(
            id = "odisha",
            name = "Odisha",
            capital = "Bhubaneswar",
            type = TerritoryType.STATE,
            zone = IndiaZone.EAST,
            primaryLanguage = "Odia",
            famousLandmark = "Konark Sun Temple & Jagannath Puri Temple",
            funFact = "The Sun Temple at Konark is shaped like a giant chariot with 24 carved stone wheels pulled by 7 horses!",
            emojiIcon = "☀️"
        ),
        IndiaState(
            id = "sikkim",
            name = "Sikkim",
            capital = "Gangtok",
            type = TerritoryType.STATE,
            zone = IndiaZone.EAST,
            primaryLanguage = "Nepali, Bhutia, Lepcha",
            famousLandmark = "Kangchenjunga (3rd highest mountain peak)",
            funFact = "Sikkim is India's first 100% organic farming state and is blessed with views of snowy Kangchenjunga!",
            emojiIcon = "🏔️"
        ),

        // North-East India (Seven Sisters)
        IndiaState(
            id = "assam",
            name = "Assam",
            capital = "Dispur",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTHEAST,
            primaryLanguage = "Assamese",
            famousLandmark = "Kaziranga National Park & Kamakhya Temple",
            funFact = "Kaziranga in Assam is the home of the famous Great Indian One-horned Rhinoceros! Assam is also famous for fragrant black tea.",
            emojiIcon = "🦏"
        ),
        IndiaState(
            id = "arunachal_pradesh",
            name = "Arunachal Pradesh",
            capital = "Itanagar",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTHEAST,
            primaryLanguage = "English, Nyishi, Adi",
            famousLandmark = "Tawang Monastery & Sela Pass",
            funFact = "Known as the 'Land of the Dawn-lit Mountains' because the sun rises here first before any other place in India!",
            emojiIcon = "🌄"
        ),
        IndiaState(
            id = "manipur",
            name = "Manipur",
            capital = "Imphal",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTHEAST,
            primaryLanguage = "Meitei (Manipuri)",
            famousLandmark = "Loktak Lake & Keibul Lamjao Floating Park",
            funFact = "Loktak Lake is the world's only floating national park, home to the rare dancing deer called Sangai!",
            emojiIcon = "🦌"
        ),
        IndiaState(
            id = "meghalaya",
            name = "Meghalaya",
            capital = "Shillong",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTHEAST,
            primaryLanguage = "Khasi, Garo, English",
            famousLandmark = "Living Root Bridges & Cherrapunji Waterfalls",
            funFact = "Meghalaya means 'Abode of Clouds'. Mawsynram here holds the world record for the highest annual rainfall on Earth!",
            emojiIcon = "🌧️"
        ),
        IndiaState(
            id = "mizoram",
            name = "Mizoram",
            capital = "Aizawl",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTHEAST,
            primaryLanguage = "Mizo & English",
            famousLandmark = "Blue Mountain (Phawngpui) & Reiek Tlang",
            funFact = "Famous for the energetic Cheraw Bamboo Dance, where dancers step gracefully between rhythmic clapping bamboo poles!",
            emojiIcon = "🎋"
        ),
        IndiaState(
            id = "nagaland",
            name = "Nagaland",
            capital = "Kohima",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTHEAST,
            primaryLanguage = "English",
            famousLandmark = "Hornbill Festival Ground & Dzukou Valley",
            funFact = "Hosts the colorful 'Hornbill Festival' celebrating traditional folk music, martial arts, and tribal crafts every December!",
            emojiIcon = "🪶"
        ),
        IndiaState(
            id = "tripura",
            name = "Tripura",
            capital = "Agartala",
            type = TerritoryType.STATE,
            zone = IndiaZone.NORTHEAST,
            primaryLanguage = "Bengali & Kokborok",
            famousLandmark = "Ujjayanta Palace & Neermahal Water Palace",
            funFact = "Neermahal is an enchanting lake palace built right in the middle of Rudrasagar Lake in Tripura!",
            emojiIcon = "👑"
        ),

        // All 8 Union Territories
        IndiaState(
            id = "andaman_nicobar",
            name = "Andaman and Nicobar Islands",
            capital = "Port Blair",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Hindi, English, Bengali",
            famousLandmark = "Cellular Jail & Radhanagar Beach (Havelock)",
            funFact = "A cluster of more than 500 emerald tropical islands in the Bay of Bengal with coral reefs and dolphins!",
            emojiIcon = "🏝️"
        ),
        IndiaState(
            id = "chandigarh_ut",
            name = "Chandigarh",
            capital = "Chandigarh",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Punjabi & Hindi",
            famousLandmark = "Rock Garden & Sukhna Lake",
            funFact = "A planned modern 'City Beautiful' designed by Swiss-French architect Le Corbusier. Its Rock Garden is made from recycled materials!",
            emojiIcon = "⛲"
        ),
        IndiaState(
            id = "dadra_daman_diu",
            name = "Dadra and Nagar Haveli and Daman and Diu",
            capital = "Daman",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.WEST,
            primaryLanguage = "Gujarati & Hindi",
            famousLandmark = "Moti Daman Fort & Jampore Beach",
            funFact = "Combined into a single Union Territory in 2020, with Portuguese colonial architecture and serene coastal beaches.",
            emojiIcon = "⛵"
        ),
        IndiaState(
            id = "delhi_nct",
            name = "Delhi (NCT)",
            capital = "New Delhi",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Hindi, English, Punjabi",
            famousLandmark = "India Gate, Red Fort & Qutub Minar",
            funFact = "New Delhi is the official national capital of India! The President's residence, Rashtrapati Bhavan, has 340 rooms!",
            emojiIcon = "🏛️"
        ),
        IndiaState(
            id = "jammu_kashmir",
            name = "Jammu and Kashmir",
            capital = "Srinagar (Summer) / Jammu (Winter)",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Kashmiri, Dogri, Hindi",
            famousLandmark = "Dal Lake (Shikaras) & Gulmarg Gondola",
            funFact = "Famous for floating gardens and wooden Shikara boats on Dal Lake, and snow-capped peaks in Gulmarg!",
            emojiIcon = "❄️"
        ),
        IndiaState(
            id = "ladakh",
            name = "Ladakh",
            capital = "Leh",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.NORTH,
            primaryLanguage = "Ladakhi & Hindi",
            famousLandmark = "Pangong Tso Lake & Hemis Monastery",
            funFact = "Known as the 'Roof of the World' and the 'Land of High Passes', with a high-altitude cold desert and double-humped camels!",
            emojiIcon = "🐪"
        ),
        IndiaState(
            id = "lakshadweep",
            name = "Lakshadweep",
            capital = "Kavaratti",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Malayalam & Jeseri",
            famousLandmark = "Agatti Atoll & Kavaratti Marine Aquarium",
            funFact = "Lakshadweep means 'One Hundred Thousand Islands'! It has pristine turquoise lagoons and vibrant coral reefs.",
            emojiIcon = "🐠"
        ),
        IndiaState(
            id = "puducherry",
            name = "Puducherry",
            capital = "Puducherry",
            type = TerritoryType.UNION_TERRITORY,
            zone = IndiaZone.SOUTH,
            primaryLanguage = "Tamil, French, English",
            famousLandmark = "Promenade Beach & Auroville Matrimandir",
            funFact = "Known as the 'French Riviera of the East' with charming French quarters, yellow colonial villas, and quiet beaches.",
            emojiIcon = "🥐"
        )
    )
}
