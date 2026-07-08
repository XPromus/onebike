import 'package:flutter/material.dart';
import 'package:onebike/types/t_rider.dart';

class Rider extends StatelessWidget {

    const Rider({
      super.key, 
      required this.place, 
      // required this.riderName, 
      // required this.nationality, 
      // required this.team, 
      required this.points, 
      required this.targetRider
    });

    final String place;
    final RiderRecord targetRider;

    // final String place;
    // final String riderName;
    // final String nationality;
    // final String team;
    final int points;

    @override
    Widget build(BuildContext context) {
      return Container(
        decoration: const BoxDecoration(
          borderRadius: BorderRadiusGeometry.all(
            Radius.circular(5)
          ),
          color: Color.fromARGB(255, 50, 50, 50)
        ),
        child: Padding(
          padding: EdgeInsetsGeometry.all(5),
          child: riderInformation(
            place,
            "${targetRider.firstName} ${targetRider.lastName}", 
            targetRider.team.name,
            "${targetRider.nationality.flagEmoji} ${targetRider.nationality.short}",
            points,
          ),
        )
      );
    }

    Row riderInformation(
      String place,
      String name,
      String team,
      String nationality,
      int points
    ) {
      return Row(
        crossAxisAlignment: .start,
        spacing: 25,
        children: [
          textField(place),
          textFieldExpanded(name),
          textFieldExpanded(team),
          textField(nationality),
          textField(points.toString()),
        ],
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
