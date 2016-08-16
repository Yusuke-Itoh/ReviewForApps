/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


   function FreezeScreen(msg) {
      scroll(0,0);
      var outerPane = document.getElementById('FreezePane');
      var innerPane = document.getElementById('InnerFreezePane');
      if (outerPane) outerPane.className = 'FreezePaneOn';
      if (innerPane) innerPane.innerHTML = msg;
   }
