import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "../model/client.model";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class ClientserviceService {

  constructor(private http:HttpClient) { }
  public getClient():Observable<Array<Client>>{
    return this.http.get<Array<Client>>(environment.backendHoste+"/clients") }

  public SearchClient(keyword:string):Observable<Array<Client>>{
    return this.http.get<Array<Client>>(environment.backendHoste+"/clients/search?keyword="+keyword) }

}
