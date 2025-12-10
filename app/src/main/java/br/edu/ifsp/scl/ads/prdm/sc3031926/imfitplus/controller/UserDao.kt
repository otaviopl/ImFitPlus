package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.controller

import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.model.User

interface UserDao {
    fun insert(user: User): Long
    fun getLatestUser(): User?
}
