# Radius Project
Fully functional Microservice built in Java, Maven and SpringBoot(with Rest API controller and services)

How to run this project :
      $ mvn spring-boot:run

How to test this project :
      After running the project using above command, just run the below curl request and match details will be printed in the terminal.
      
      curl "http://localhost:8080/search-controller/search?lat=18.1224&lon=79.1102&minBudget=10000&maxBudget=20000&minBeds=2&maxBeds=4&minBaths=2&maxBaths=3"
  
  

Assumptions made while building this project:

  Maximum % contributions assumed for the given 4 parameters:
  1. Distance : 30%
  2. Budget : 30%
  3. BedRooms : 20%
  4. BathRooms : 20%
  
 ------------------------------------------------------------------------------------------------------------------------------------
  
 Any Requirement Match is considered as useful only if total match percent is greater than 40%
 
 ------------------------------------------------------------------------------------------------------------------------------------
 
 Assumptions for Distance match calculation:
 
   Distance b/w SearchRequirement and Property---------------------------- % Contribution
   
    1. Distance <= 2 Miles                                         30%
    2. 2 Miles < Distance <= 5 Miles                               25%
    3. 5 Miles < Distance <= 10 Miles                              20% 
    4. Distance > 10                                               0% (Not a Match) 
    
    
  ------------------------------------------------------------------------------------------------------------------------------------
    
 Assumptions for Budget match calculation:    
    
    If Both Min and Max Budget are provided then
    
      If property price lies in between Min and Max budgets then 30% contribution

      If not then below contributions
      
       if price lies in MinBudget - 10%(MinBudget) or MaxBudget + 10%(MaxBudget) then 25% contribution
       if price lies in MinBudget - 20%(MinBudget) or MaxBudget + 20%(MaxBudget) then 20% contribution
       if price lies in MinBudget - 25%(MinBudget) or MaxBudget + 25%(MaxBudget) then 15% contribution
       else 0% contribution (Not a match)
       
    if only Max Budget is provided then
    
       if price lies in MaxBudget + 10%(MaxBudget) then 30% contribution
       if price lies in MaxBudget + 20%(MaxBudget) then 25% contribution
       if price lies in MaxBudget + 25%(MaxBudget) then 20% contribution
       else 0% contribution (Not a match)
       
    if only Max Budget is provided then   
    
       if price lies in MinBudget - 10%(MinBudget) then 30% contribution
       if price lies in MinBudget - 20%(MinBudget) then 25% contribution
       if price lies in MinBudget - 25%(MinBudget) then 20% contribution
       else 0% contribution (Not a match)
  
  ------------------------------------------------------------------------------------------------------------------------------------
    
 Assumptions for BedRooms match calculation: (considering same for BathRooms as well)   
 
  if both Min and Max BedRooms are provided then:
  
    If property bedrooms lies in between Min and Max bedrooms then 20% contribution
    
     If not then below contributions
     
       if bedRooms lies in Min BedRooms - 1 or Max BedRooms + 1 then 15% contribution
       if bedRooms lies in Min BedRooms - 2 or Max BedRooms + 2 then 10% contribution
       else 0% contribution (Not a match)
       
  if only Max BedRooms is provided then
  
       if BedRooms lies in Max BedRooms + 1 then 20% contribution
       if BedRooms lies in Max BedRooms + 2 then 15% contribution
       else 0% contribution (Not a match)     
       
  if only Min BedRooms is provided then
  
       if BedRooms lies in Min BedRooms - 1 then 20% contribution
       if BedRooms lies in Min BedRooms - 2 then 15% contribution
       else 0% contribution (Not a match)     
       
  ------------------------------------------------------------------------------------------------------------------------------------
  
  Assumptions for BathRooms match calculation: Same as above 
   
