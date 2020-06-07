# TestJava
 
Bonjour,
pour commecer , j ai pas utiliser Cassandra comme SGBD , j avais des problemes de compatibilite avec ma version java ,donc j ai obter pour mango .

description des package :
 1-SendWordsAppServerConfig : application pour recuperer les properties a partir de Icloud ,tous les properties sont configurer sont configurer dedans .
 2-SendWordsAppDiscovryService : application eureka pour gerer les instance des applications et et gerer le balancement entre les applications ,
 3-SendWordsAppZuulGetway : application   passerelle de routage et filtrage des requetes , dans mon cas j ai filter juste les URL que je veux ,{/client/createMessage ,/client/find,/client/find/word**} , j aller faire l authentification , mais j ai pas eu assez de temps .
 4-SendWordsAppClient :application client qui recoi les requete d un client envoyer les dans une topic , et ecoute une autre topic pour recuperer les message et les sauvegarder dans la BD.
 5-SendWordsAppProcessor :microservice processor recupere les message depuis la topic chaque 1 minute et renvoi dans une autre topic les message concatener .
 
 note : 
 *ordre d execution :
 en premier 
   1 -SendWordsAppServerConfig
   2- SendWordsAppDiscovryService ,
   3-SendWordsAppZuulGetway
   4-SendWordsAppClient
   5-SendWordsAppProcessor
   
 *les topic ce cree automatique lorsque en execute le microservice SendWordsAppClient .
 
 * URL postman pour les requetes : https://www.getpostman.com/collections/7d7dad7865184a58ab4c
 
 
 
