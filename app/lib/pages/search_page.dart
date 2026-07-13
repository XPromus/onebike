import 'package:flutter/material.dart';
import 'package:onebike/api/team.dart';
import 'package:onebike/components/team_search_widget.dart';

class SearchPage extends StatefulWidget {
  
  const SearchPage(
    {
      super.key
    }
  );
  
  @override
  State<StatefulWidget> createState() => _SearchPageState();

}

class _SearchPageState extends State<SearchPage> {

  late Future<List<Team>> teams; 

  @override
  void initState() {
    super.initState();
    teams = fetchTeams();
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(10),
      child: Column(
        spacing: 10,
        children: [
          TextField(
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              fillColor: Theme.of(context).colorScheme.secondary,
              label: Text("Search"),
              contentPadding: EdgeInsetsGeometry.all(5),
            ),
          ),
          FutureBuilder<List<Team>>(
            future: teams, 
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Column(
                  spacing: 5,
                  children: snapshot.data!.map(
                    (team) => TeamSearchEntry(team: team)
                  ).toList(),
                );
              } else if (snapshot.hasError) {
                return Text("${snapshot.error}");
              }
          
              return CircularProgressIndicator();
            },
          ),
        ],
      ),
    );
  }

}
