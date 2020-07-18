import React, { Component } from "react";
import {
  MDBNavbar, MDBNavbarBrand, MDBNavbarNav, MDBNavItem, MDBNavLink, MDBNavbarToggler, MDBCollapse, MDBDropdown,
  MDBDropdownToggle, MDBDropdownMenu, MDBDropdownItem, MDBIcon
} from "mdbreact";
import { NavLink } from 'react-router-dom';
import { connect } from 'react-redux';
import history from '../redux/history';
class Header extends Component {
  constructor(props) {
    super(props);
    this.state = {
      status: ''
    }
    this.Logout = this.Logout.bind(this)
  }
  state = {
    isOpen: false
  };

  toggleCollapse = () => {
    this.setState({ isOpen: !this.state.isOpen });
  }
  Logout = () => {
    localStorage.clear();

    window.location.href = '/'
  }

  render() {
    console.log("prrops in  header", this.props);
    console.log(localStorage.getItem("role"))
    if (localStorage.getItem("isLoggedIn") && (localStorage.getItem("role") == "Admin") || (localStorage.getItem("role") == "Pmo") || (localStorage.getItem("role") == "Poc")) {
      return (
        <div class="mb-2">
          <MDBNavbar style={{ backgroundColor: '#0000b3' }} dark expand="md">
            <MDBNavbarBrand className="navbar-b">
              <strong className="white-text"> <i className="fa fa-handshake fa-rotate-40" /> Outreach</strong>&nbsp;FMS
            </MDBNavbarBrand>
            <MDBNavbarToggler onClick={this.toggleCollapse} />
            <MDBCollapse id="navbarCollapse3" isOpen={this.state.isOpen} navbar>
              <MDBNavbarNav left>
                <MDBNavItem className ="navbar-nav">

                  <MDBNavLink exact to="/dashboard"  > <i className="fa fa-plus-square" /> &nbsp;DashBoard</MDBNavLink>
                </MDBNavItem>
                <MDBNavItem className ="navbar-nav">

                  <MDBNavLink exact to="/event"> <i className="fa fa-arrow-circle-right" /> &nbsp; Events</MDBNavLink>
                </MDBNavItem>
                <MDBNavItem className ="navbar-nav">

                  <MDBNavLink to="/report"> <i className="fa fa-file-excel" /> &nbsp; Reports</MDBNavLink>
                </MDBNavItem>
                <MDBNavItem className ="navbar-nav">
                  <MDBDropdown >
                    <MDBDropdownToggle nav caret>
                      <i className="fa fa-cog" /> &nbsp;
                <div className="d-none d-md-inline">Configuration</div>
                    </MDBDropdownToggle>
                    <MDBDropdownMenu >
                      <NavLink to="/role" ><MDBDropdownItem>Roles</MDBDropdownItem></NavLink>
                      <NavLink to="/questions"><MDBDropdownItem >Feedback Questions</MDBDropdownItem> </NavLink>
                    </MDBDropdownMenu>
                  </MDBDropdown>
                </MDBNavItem>
              </MDBNavbarNav>
              <MDBNavbarNav right>
                <MDBNavItem>
                  <MDBDropdown>
                    <MDBDropdownToggle nav caret>
                      <MDBIcon/> <i class="fa fa-user-circle fa-size" aria-hidden="true"></i>&nbsp; {localStorage.getItem("username")} ({localStorage.getItem("role")})
                    </MDBDropdownToggle>
                    <MDBDropdownMenu className="dropdown-menu">
                      <MDBDropdownItem onClick={this.Logout}>Log Out</MDBDropdownItem>
                    </MDBDropdownMenu>
                  </MDBDropdown>
                </MDBNavItem>
              </MDBNavbarNav>
            </MDBCollapse>
          </MDBNavbar>
        </div>
      );
    }
    else if (localStorage.getItem("role") == "User") {
      return (
        <div class="mb-2">
          <MDBNavbar style={{ backgroundColor: '#0000b3' }} dark expand="md">
            <MDBNavbarBrand>
            <strong className="white-text"> <i className="fa fa-handshake fa-rotate-40" /> Outreach</strong>&nbsp;FMS
            </MDBNavbarBrand>
            <MDBNavbarToggler onClick={this.toggleCollapse} />
            <MDBCollapse id="navbarCollapse3" isOpen={this.state.isOpen} navbar>
              <MDBNavbarNav right>
                <MDBNavItem>
                  <MDBDropdown>
                    <MDBDropdownToggle nav caret>
                      <MDBIcon icon="user" /> &nbsp; {localStorage.getItem("username")} ({localStorage.getItem("role")})
                    </MDBDropdownToggle>
                    <MDBDropdownMenu className="dropdown-menu">
                      <MDBDropdownItem onClick={this.Logout}>Log Out</MDBDropdownItem>
                    </MDBDropdownMenu>
                  </MDBDropdown>
                </MDBNavItem>
              </MDBNavbarNav>
            </MDBCollapse>
          </MDBNavbar>
        </div>
      );
    }
    else {
      return null;
    }

  }
}

const mapStateToProps = state => {
  return {
    Users: state.userData

  };
};
export default connect(mapStateToProps, null)(Header);