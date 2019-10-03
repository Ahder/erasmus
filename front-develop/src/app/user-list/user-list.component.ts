import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';
import {Router} from '@angular/router';
import {UserListService} from './user-list.service';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[];
  dataSource: MatTableDataSource<User>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public userService: UserService, private router: Router, private userListService: UserListService) { }

  ngOnInit() {
    this.userListService.findAllUsers().subscribe( (res: User[]) => {
      console.log(res);

      this.displayedColumns = Object.keys(res[0]).filter(
        key => ['password', 'id', 'enabled', 'username', 'resetToken'].indexOf(key) === -1
      );
      this.dataSource = new MatTableDataSource(res);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }, error1 => console.log(error1));

  }

  showUser(element: User) {
    console.log(element);
    this.userService.setUser(element);
    this.router.navigateByUrl('/utilisateur');
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
