import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AddPostService } from '../add-post.service';
import { PostPayload } from '../add-post/post-payload';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Observable<Array<PostPayload>>;
  constructor(private postService: AddPostService,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.posts = this.postService.getAllPosts();
  }

}
