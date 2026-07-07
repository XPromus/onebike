import 'package:flutter/material.dart';
import 'package:onebike/pages/home_page.dart';
import 'package:onebike/pages/search_page.dart';
import 'package:onebike/pages/user_page.dart';

void main() {
  runApp(const OneBike());
}

class OneBike extends StatelessWidget {
  
  const OneBike(
    {
      super.key
    }
  );

  @override
  Widget build(
    BuildContext context
  ) {
    return MaterialApp(
      title: 'OneBike',
      theme: ThemeData(
        colorScheme: .fromSeed(seedColor: Color.fromARGB(255, 0, 128, 255)),
      ),
      home: const MainPage(title: 'OneBike'),
    );
  }
}

class MainPage extends StatefulWidget {
  
  const MainPage(
    {
      super.key, 
      required this.title
    }
  );

  final String title;

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {

  int currentPageIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: NavigationBar(
        onDestinationSelected: (int index) {
          setState(() {
            currentPageIndex = index;
          });
        },
        destinations: const <Widget>[
          NavigationDestination(
            icon: Icon(Icons.home), 
            label: "Home"
          ),
          NavigationDestination(
            icon: Icon(Icons.search), 
            label: "Search"
          ),
          NavigationDestination(
            icon: Icon(Icons.person), 
            label: "User"
          ),
        ],
        selectedIndex: currentPageIndex,
      ),
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(
          widget.title, 
          style: TextStyle(
            fontWeight: FontWeight.bold
          ),
        ),
      ),
      body: <Widget>[
        HomePage(),
        SearchPage(),
        UserPage(),
      ][currentPageIndex],
    );
  }
}
