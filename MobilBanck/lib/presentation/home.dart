import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'client.dart';

class Home extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text("DejiatalBancking"),
      ),
      body:
      Container(
        child: Center(
          child: ElevatedButton(
            child: const Text('Liste des clients'),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => CustomerPage()),
              );
            },
          ),
        ),
        decoration: BoxDecoration(
    image: DecorationImage(
    image: NetworkImage(
        "https://th.bing.com/th/id/R.fbeb6e44d76d81cbe37a9a35a2ad7a57?rik=S0C8JWoVZWjIEA&pid=ImgRaw&r=0"
    ),
    fit: BoxFit.cover
    )
    ),

    ),
    );

  }

}