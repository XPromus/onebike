import 'package:flutter/material.dart';
import 'package:onebike/cup/cup.dart';

class HomePage extends StatelessWidget {
  
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Cup(
      cupName: "Lausitzcup", 
      cupYear: "2026",
    );
  }

}
