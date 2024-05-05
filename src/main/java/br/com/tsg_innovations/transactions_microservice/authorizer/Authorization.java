package br.com.tsg_innovations.transactions_microservice.authorizer;

record Authorization(String message){
    public boolean isAuthorized(){
        return message.equals("Autorizado");
    }
}