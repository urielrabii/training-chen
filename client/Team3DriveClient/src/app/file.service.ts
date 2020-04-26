import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { File } from './file';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private filesUrl = 'http://localhost:8080/files';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })  
  };
  constructor(private http: HttpClient) { }

  /** GET files from the server */
getFiles(): Observable<File[]> {
  return this.http.get<File[]>(this.filesUrl)
      .pipe(
        catchError(this.handleError<File[]>('getFiles', []))
      );
  }
 
/** GET file by id. Will 404 if id not found */
getFile(name: string): Observable<File> {
  const url = `${this.filesUrl}/${name}`;
  return this.http.get<File>(url).pipe(
    catchError(this.handleError<File>(`getFile name=${name}`))
  );
}

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /* GET files whose name contains search term */
  searchFiles(term: string): Observable<File[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<File[]>(`${this.filesUrl}/?name=${term}`);
  }
}
