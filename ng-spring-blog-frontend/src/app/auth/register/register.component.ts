import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.formBuilder.group({
      username:'',
      email:'',
      password:'',
      confirmPassword:''
    });
   }

  ngOnInit(): void {
  }

  onSubmit() {
    this.registerForm.get('username').value;
    this.registerForm.get('email').value;
    this.registerForm.get('password').value;
    this.registerForm.get('confirmPassword').value;

  }
}
