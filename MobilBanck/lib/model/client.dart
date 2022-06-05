
class Clientt{
  int id;
  String name;
  String email;


  Clientt(this.id,this.name, this.email);

  factory Clientt.fromJson(dynamic json) {
    return Clientt(json['id'] as int, json['name'] as String,json['email'] as String);
  }


}