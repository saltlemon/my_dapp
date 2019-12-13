pragma solidity ^0.4.25;

import "./Table.sol";

contract Asset {
    

   
    mapping(address => uint) public balances;
    mapping(address => mapping(address => uint)) bonds;
    mapping(address => uint) public ownBonds;


    function getBalance(address _address) public returns(uint){
	    return balances[_address];
    }

    function getBond(address _address) public returns(uint){
	    return ownBonds[_address];
    }

    function issue(address _address,uint amount) public {
	    balances [_address]+=amount;
    }

    function send_money(address from_address,address to_address ,uint amount) public{
        if(balances[from_address]<amount)return;    
        balances[from_address]-=amount;
    	balances[to_address]+=amount;
    }

    function send_bond(address from_address,address to_address,address borrower_address,uint amount) public{
        if(bonds[from_address][to_address]<amount)return;    
    	bonds[from_address][borrower_address]-=amount;
    	bonds[to_address][borrower_address]+=amount;
        ownBonds[from_address]-=amount;
    	ownBonds[to_address]+=amount;
    }

    function borrow(address borrower_address,address lander_address,uint amount) public{
        if(balances[lander_address]<amount)return;
        balances[lander_address]-=amount;
    	balances[borrower_address]+=amount;
    	bonds[lander_address][borrower_address]+=amount;
    	ownBonds[lander_address]+=amount;
    }

    function pay(address borrower_address,address lander_address,uint amount) public{
        if(balances[borrower_address]<amount)
        balances[borrower_address]-=amount;
    	balances[lander_address]+=amount;
	    bonds[lander_address][borrower_address]-=amount;
    	ownBonds[lander_address]-=amount;
    }

    function financ(address bank_address,address name_address,uint amount) public returns(uint){
        if(balances[name_address]+ownBonds[name_address]<amount)return;
    	balances[name_address]+=amount;
    	bonds[bank_address][name_address]+=amount;
    }
}


















