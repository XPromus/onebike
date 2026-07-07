import 'package:flutter/material.dart';
import 'package:onebike/types/t_team.dart';

class TeamPage extends StatelessWidget {
  
  const TeamPage({
    super.key, 
    required this.targetTeam
  });

  final TeamRecord targetTeam;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: EdgeInsetsGeometry.all(50),
        child: Column(
          children: [
            CircleAvatar(
              minRadius: 50,
              child: Image.network(
                width: 100,
                height: 100,
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fyt3.googleusercontent.com%2Fytc%2FAIdro_mxzhZN-OQX2T7r8IIlo2fHWLZ4jGESis3p4xvcYx-wDA%3Ds900-c-k-c0x00ffffff-no-rj&f=1&nofb=1&ipt=ba254d70a01bcad1f8e73ffb111cce25784f5dc508801c99128f2de68c0666d3"
              ),
            ),
            Text(
              targetTeam.name,
              style: TextStyle(fontSize: 30),
            )  
          ],
        ),
      ),
    );
  }

}
