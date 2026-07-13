import 'dart:convert';
import 'package:http/http.dart' as http;

Future<List<Team>> fetchTeams() async {
  final response = await http.get(
    Uri.parse("http://localhost:8080/teams"),
    headers: {
      "Accept": "application/json",
    }
  );

  if (response.statusCode == 200) {
    try {
      var json = jsonDecode(response.body) as List<dynamic>;
      var teams = Team.teamListFromJsonArray(json);
      return teams;
    } catch(e) {
      print(e);
      return [];
    }
  } else {
    throw Exception("Failed to load Team.");
  }
}

class Team {
  final int id;  
  final String teamName;
  final String shortName;
  final String teamDescription;
  final int nationalityId;
  final List<int> riderIds;

  Team(
    {
      required this.id, 
      required this.teamName, 
      required this.shortName, 
      required this.teamDescription, 
      required this.nationalityId, 
      required this.riderIds
    }
  );

  factory Team.fromJson(
    Map<String, dynamic> json
  ) {
    
    var team = Team(
      id: json["id"] as int, 
      teamName: json["teamName"] as String, 
      shortName: json["shortName"] as String, 
      teamDescription: json["teamDescription"] as String, 
      nationalityId: json["nationalityId"] as int, 
      riderIds: []
    );

    return team;
  }

  static List<Team> teamListFromJsonArray(
    List<dynamic> jsonArray
  ) {
    return jsonArray
      .map((e) => Team.fromJson(e as Map<String, dynamic>))
      .toList();
  }
}


