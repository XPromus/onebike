import 'package:flutter/material.dart';
import 'package:onebike/types/t_rider.dart';

class RiderPage extends StatelessWidget {
  
  const RiderPage({
    super.key, 
    required this.targetRider
  });

  final RiderRecord targetRider;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: EdgeInsetsGeometry.all(50),
        child: Column(
          children: [
            CircleAvatar(
              minRadius: 50,
              child: Icon(
                Icons.person,
                size: 50, 
              ),
            ),
            Text(
              "${targetRider.firstName} ${targetRider.lastName}",
              style: TextStyle(fontSize: 35),
            ),
            TextButton(
              onPressed: () => {}, 
              child: Text(
                targetRider.team.name,
                style: TextStyle(fontSize: 20),
              ),
            ),
            Text(
              "${targetRider.nationality.name} ${targetRider.nationality.flagEmoji}",
              style: TextStyle(fontSize: 20),
            ),
          ],
        ),
      ),
    );
  }

}
