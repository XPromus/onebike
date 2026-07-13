import 'package:flutter/material.dart';
import 'package:onebike/api/team.dart';

class TeamSearchEntry extends StatelessWidget {

  final Team team;

  const TeamSearchEntry(
    {
      super.key, 
      required this.team
    }
  );

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const BoxDecoration(
        borderRadius: BorderRadiusGeometry.all(
          Radius.circular(5)
        ),
        color: Color.fromARGB(255, 50, 50, 50),
      ),
      child: Padding(
        padding: const EdgeInsets.all(5),
        child: Row(
          crossAxisAlignment: .start,
          spacing: 25,
          children: [
            textFieldExpanded(team.teamName),
            textField(team.shortName),
          ],
        ),
      ),
    );
  }

  Flexible textField(
    String text
  ) {
    return Flexible(
      child: Text(
        text,
        style: TextStyle(color: Colors.white),
      ),
    );
  }

  Expanded textFieldExpanded(
    String text
  ) {
    return Expanded(
      child: Text(
        text,
        style: TextStyle(color: Colors.white),
      )
    );
  }

}
