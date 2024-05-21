import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  constructor(public authService: AuthService, private router: Router) {
  }
  ngOnInit(): void {

  }

  handleLogout() {
    this.authService.logout();
    this.router.navigateByUrl("/login");

  }
}
