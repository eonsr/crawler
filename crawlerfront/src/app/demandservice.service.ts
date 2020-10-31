import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const HEADER = {
  headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Methods': 'GET',
      'Access-Control-Allow-Headers': 'Content-Type, Authorization'
  }),
};

@Injectable({
  providedIn: 'root'
})
export class DemandserviceService {

  constructor(protected http: HttpClient) { }

  nameSearch : string;

  url : string;

  getDemandsByName(nameSearch): Observable<any> {

    console.log(nameSearch);

    this.url = 'http://localhost:8080/api/v1/crawler/findInfoByName?name=' + nameSearch;

    return this.http.get(this.url, HEADER);

  }

}
