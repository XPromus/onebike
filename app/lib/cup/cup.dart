import 'package:flutter/material.dart';
import 'package:onebike/rider.dart';

class Cup extends StatelessWidget {

  const Cup(
    {
      super.key, 
      required this.cupName, 
      required this.cupYear
    }
  );

  final String cupName;
  final String cupYear;

  @override
  Widget build(BuildContext context) {

    final VoidCallback? onPressed = true ? () {} : null;

    const List<String> races = [
      "Kamenz",
      "Klettwitz",
      // "Lampertswalde",
      // "Wittichenau",
      "Spremberg",
      "Görlitz",
      // "Peitz",
      // "Ponickau"
    ];

    return Column(
      children: [
        Text(
          "$cupName $cupYear",
          style: TextStyle(fontSize: 25),
        ),

        Padding(
          padding: EdgeInsetsGeometry.all(10),
          child: Row(
            spacing: 5,
            children: races.map((race) =>
              OutlinedButton(
                onPressed: onPressed, 
                child: Text(race)
              ),
            ).toList(),
          ),
        ),

        Expanded(
          child: Padding(
            padding: EdgeInsetsGeometry.all(10),
            child: riderList(),
          ),
        ),
      ],
    );
  }

  ListView riderList() {
    return ListView(
      children: <Rider>[
        Rider(
          place: "1", 
          riderName: "Scott MacEwan", 
          nationality: "🏴󠁧󠁢󠁳󠁣󠁴󠁿​ (SCO)", 
          team: "Sebamed Racing Team", 
          points: 529
        ), Rider(
          place: "2", 
          riderName: "Marcel Schmidt", 
          nationality: "🇩🇪​ (GER)", 
          team: "Mobile Krankenkasse Cycling Team", 
          points: 409
        ), Rider(
          place: "3", 
          riderName: "Petr Kniha", 
          nationality: "🇨🇿​ (CZE)", 
          team: "Unlimited KM Trading CZ", 
          points: 395
        ), Rider(
          place: "4", 
          riderName: "Filip Helcl", 
          nationality: "🇨🇿​ (CZE)", 
          team: "Unlimited KM Trading CZ", 
          points: 445
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ), Rider(
          place: "5", 
          riderName: "Florian Jung", 
          nationality: "🇩🇪​ (GER)", 
          team: "Post SV Görlitz", 
          points: 426
        ),
      ],
    );

    // return Column(
    //   spacing: 5,
    //   children: [
    //     Rider(
    //       place: "1", 
    //       riderName: "Scott MacEwan", 
    //       nationality: "🏴󠁧󠁢󠁳󠁣󠁴󠁿​ (SCO)", 
    //       team: "Sebamed Racing Team", 
    //       points: 529
    //     ), Rider(
    //       place: "2", 
    //       riderName: "Marcel Schmidt", 
    //       nationality: "🇩🇪​ (GER)", 
    //       team: "Mobile Krankenkasse Cycling Team", 
    //       points: 409
    //     ), Rider(
    //       place: "3", 
    //       riderName: "Petr Kniha", 
    //       nationality: "🇨🇿​ (CZE)", 
    //       team: "Unlimited KM Trading CZ", 
    //       points: 395
    //     ), Rider(
    //       place: "4", 
    //       riderName: "Filip Helcl", 
    //       nationality: "🇨🇿​ (CZE)", 
    //       team: "Unlimited KM Trading CZ", 
    //       points: 445
    //     ), Rider(
    //       place: "5", 
    //       riderName: "Florian Jung", 
    //       nationality: "🇩🇪​ (GER)", 
    //       team: "Post SV Görlitz", 
    //       points: 426
    //     ),
    //   ],
    // );
  }

}