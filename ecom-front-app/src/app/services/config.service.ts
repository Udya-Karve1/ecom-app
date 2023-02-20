import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})

export class ConfigService {

    constructor(private http: HttpClient){}

    urlPrefix = 'http://localhost:8888/property';

    getAllApplicationList() {
        return this.http.get(this.urlPrefix + '/application/all')
            .pipe(map(response => {return response}));
    }

    getAllProfileList() {
        return this.http.get(this.urlPrefix + '/profile/all')
            .pipe(map(response => {return response}));        
    }


    getAllPropertyForApplication() {
        return this.http.get(this.urlPrefix + '/all')
            .pipe(map(response => {return response}));        
    }

}