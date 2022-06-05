import 'package:dejitalbancking/presentation/client.dart';
import 'package:dejitalbancking/presentation/home.dart';
import 'package:dejitalbancking/reposetory/blocReosetory.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'Bloc/clientBloc.dart';
import 'Bloc/clientState.dart';
void main() {
  runApp(const MyApp());
}


class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return  MultiBlocProvider(
      providers: [
        BlocProvider(create: (context)=>CustomerBloc(new CustomerState(requested: Requested.NONE, customers: [], errorMessage: ''), new CustomerRepository())),

      ],
      child: MaterialApp(
        theme: ThemeData(
            primarySwatch: Colors.deepPurple
        ),
        routes: {
          "/customers": (context) => CustomerPage(),
          "/Home": (context) => Home(),
        },
        initialRoute: "/Home",
      ),

    );
  }
}