package com.invictus.motivationalquotes.data

import com.invictus.motivationalquotes.db.MotivationSharedPreferences

object ImageList {


    fun getSelectedImage():String = imageList()[MotivationSharedPreferences.SELECTED_IMAGE]


    fun imageList(): List<String>{
        val list =  arrayListOf(
            "https://images.pexels.com/photos/7919366/pexels-photo-7919366.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1459505/pexels-photo-1459505.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "https://images.pexels.com/photos/5829761/pexels-photo-5829761.jpeg?auto=compress&cs=tinysrgb&w=600",
            "https://images.pexels.com/photos/4220967/pexels-photo-4220967.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3224156/pexels-photo-3224156.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3214944/pexels-photo-3214944.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2627945/pexels-photo-2627945.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2414442/pexels-photo-2414442.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1525589/pexels-photo-1525589.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/4591254/pexels-photo-4591254.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/5103756/pexels-photo-5103756.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/5282269/pexels-photo-5282269.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/4388593/pexels-photo-4388593.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/4394104/pexels-photo-4394104.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3621344/pexels-photo-3621344.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/2524874/pexels-photo-2524874.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1144687/pexels-photo-1144687.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1699030/pexels-photo-1699030.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/2440299/pexels-photo-2440299.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2365457/pexels-photo-2365457.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2309266/pexels-photo-2309266.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/15379/pexels-photo.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/884547/pexels-photo-884547.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/2832034/pexels-photo-2832034.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2770933/pexels-photo-2770933.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3464632/pexels-photo-3464632.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/6843561/pexels-photo-6843561.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/3225517/pexels-photo-3225517.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/1761279/pexels-photo-1761279.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1770809/pexels-photo-1770809.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1820563/pexels-photo-1820563.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/1547813/pexels-photo-1547813.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3408552/pexels-photo-3408552.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3331094/pexels-photo-3331094.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2792157/pexels-photo-2792157.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/4218704/pexels-photo-4218704.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/5694144/pexels-photo-5694144.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3772239/pexels-photo-3772239.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/4218701/pexels-photo-4218701.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/5238645/pexels-photo-5238645.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3811731/pexels-photo-3811731.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/3772254/pexels-photo-3772254.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/6389075/pexels-photo-6389075.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/5068913/pexels-photo-5068913.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3772299/pexels-photo-3772299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/5480744/pexels-photo-5480744.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/6039243/pexels-photo-6039243.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/5691866/pexels-photo-5691866.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/1545743/pexels-photo-1545743.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/2227774/pexels-photo-2227774.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2050244/pexels-photo-2050244.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2363807/pexels-photo-2363807.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/678725/pexels-photo-678725.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2467287/pexels-photo-2467287.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3221159/pexels-photo-3221159.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2418992/pexels-photo-2418992.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1974520/pexels-photo-1974520.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3426870/pexels-photo-3426870.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/3356416/pexels-photo-3356416.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/1122462/pexels-photo-1122462.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2343468/pexels-photo-2343468.jpeg?auto=compress&cs=tinysrgb&w=1600",
            "https://images.pexels.com/photos/2227832/pexels-photo-2227832.jpeg?auto=compress&cs=tinysrgb&w=1600",
            )
        return list
    }


}