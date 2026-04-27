package com.example

import grails.compiler.GrailsCompileStatic
import groovy.transform.ToString
import org.hibernate.Hibernate
import org.hibernate.Session

@GrailsCompileStatic
@ToString(cache = true, includeNames = true, includePackage = false)
class UserRole implements Serializable {

    private static final long serialVersionUID = 1

    User user
    Role role

    @Override
    boolean equals(Object other) {
        if (other instanceof UserRole) {
            other.userId == user?.id && other.roleId == role?.id
        } else {
            false
        }
    }

    @Override
    int hashCode() {
        int hashCode = 17
        if (user) {
            hashCode = 31 * hashCode + Hibernate.getClass(user).hashCode()
        }
        if (role) {
            hashCode = 31 * hashCode + Hibernate.getClass(role).hashCode()
        }
        hashCode
    }

    static UserRole get(long userId, long roleId) {
        criteriaFor(userId, roleId).get()
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count() > 0
    }

    private static grails.gorm.DetachedCriteria<UserRole> criteriaFor(long userId, long roleId) {
        UserRole.where {
            user == User.load(userId) &&
                    role == Role.load(roleId)
        }
    }

    static UserRole create(User user, Role role, boolean flush = false) {
        UserRole userRole = new UserRole(user: user, role: role)
        userRole.save(flush: flush, insert: true)
        userRole
    }

    static boolean remove(User u, Role r, boolean flush = false) {
        if (u == null || r == null) {
            return false
        }

        Number rowCount = UserRole.where { user == u && role == r }.deleteAll()
        if (flush) {
            UserRole.withSession { Session session -> session.flush() }
        }
        rowCount.intValue() > 0
    }

    static int removeAll(User u) {
        u == null ? 0 : UserRole.where { user == u }.deleteAll() as int
    }

    static int removeAll(Role r) {
        r == null ? 0 : UserRole.where { role == r }.deleteAll() as int
    }

    static constraints = {
        user nullable: false
        role nullable: false, validator: { Role r, UserRole ur ->
            if (ur.user?.id) {
                if (UserRole.exists(ur.user.id, r.id)) {
                    return ['userRole.exists']
                }
            }
        }
    }

    static mapping = {
        id composite: ['user', 'role']
        version false
    }
}
