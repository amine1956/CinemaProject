
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../Bloc/ClientEvent.dart';
import '../Bloc/clientBloc.dart';
import '../Bloc/clientState.dart';

class CustomerPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("Clients"),
      ),
      body: Column(
        children: [
          Center(
            child:  ElevatedButton(
              onPressed: () =>
              {context.read<CustomerBloc>().add(ListCustomerEvent())
              },
              child: Text("Reload"),
            ),
          ),

          Expanded(child: BlocBuilder<CustomerBloc, CustomerState>(
            builder: (context, state) {
              if (state.requested == Requested.Loading) {
    return const Center(
    child: SizedBox(
    child:
    CircularProgressIndicator(color: Colors.lightGreen),
    height: 10.0,
    width: 200.0));
    }              else if (state.requested == Requested.Loaded) {
                return ListView.builder(
                    shrinkWrap: true,
                    itemCount: state.customers.length,
                    itemBuilder: ((context, index) {
                      return Container(
                        decoration: BoxDecoration( //                    <-- BoxDecoration
                          border: Border(bottom: BorderSide()),
                        ),
                        child:  ListTile(
                            onTap: (){

                            },
                            leading: CircleAvatar(
                                child: Text("${state.customers[index].name}"
                                    .substring(0, 1))),
                            title: Text("${state.customers[index].name}"),
                            trailing: IconButton(
                              icon: Icon(Icons.delete),
                              onPressed: () {

                              },
                            )) ,
                      );


                    })
                );
              } else if (state.requested == Requested.Error) {
                return Column(
                  children: [
                    Text("${state.errorMessage}"),
                    ElevatedButton(
                        onPressed: () => {

                        },
                        child: Text("reload"))
                  ],
                );
              } else {
                return Center(
                  child: Text("Nothing"),
                );
              }
            },
          ))

        ],
      ),
    );
  }

}