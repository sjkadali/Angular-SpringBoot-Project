import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostPayload } from './add-post/post-payload';

@Injectable({
  providedIn: 'root'
})
export class AddPostService {

  constructor(private httpClient: HttpClient) {

   }
   addPost(postPayload: PostPayload) {
     return this.httpClient.post('http://localhost:8080/api/posts', postPayload);
   }

   getAllPosts(): Observable<Array<PostPayload>> {
    const posts: Observable<Array<PostPayload>> =  this.httpClient.get<Array<PostPayload>>("http://localhost:8080/api/posts/all");
    console.log(posts);
    return posts;
    }

    getPost(permLink: Number): Observable<PostPayload> {
      return this.httpClient.get<PostPayload>('http://localhost:8080/api/posts/get/' +permLink);
    }
}
