import 'package:flutter/material.dart';
import 'package:onebike/pages/content/rider_page.dart';
import 'package:onebike/types/t_nation.dart';
import 'package:onebike/types/t_team.dart';

class UserPage extends StatelessWidget {
  
  const UserPage({super.key});

  @override
  Widget build(BuildContext context) {

    const NationRecord germany = (
      name: "Germany",
      short: "GER",
      flagEmoji: "🇩🇪"
    );

    const TeamRecord postSVG = (
      name: "Post SV Görlitz",
      country: germany,
      logoURL: "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fyt3.googleusercontent.com%2Fytc%2FAIdro_mxzhZN-OQX2T7r8IIlo2fHWLZ4jGESis3p4xvcYx-wDA%3Ds900-c-k-c0x00ffffff-no-rj&f=1&nofb=1&ipt=ba254d70a01bcad1f8e73ffb111cce25784f5dc508801c99128f2de68c0666d3",
    );

    const TeamRecord spTeam = (
      name: "Mobile Krankenkasse Cycling Team",
      country: germany,
      logoURL: ""
    );

    // return TeamPage(
    //   targetTeam: postSVG
    // );

    return RiderPage(
      targetRider: (
        firstName: "Marcel",
        lastName: "Schmidt",
        nationality: germany,
        team: spTeam
      )
    );
  }

}