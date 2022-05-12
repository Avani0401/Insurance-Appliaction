export default class Underwiters {
    userName : string;
    userFirstName :string;
    userLastName :string;
    email:string;
    phone:string;
    role: Array<any>;
    enabled:boolean;

    constructor(userName:string, userFirstName:string, userLastName:string, email:string,
        phone:string, role:Array<any>, enabled:boolean
        ){
        this.userName =  userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.enabled = enabled;
    }
}