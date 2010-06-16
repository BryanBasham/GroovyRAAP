package org.raap.domain

class CountyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [countyInstanceList: County.list(params), countyInstanceTotal: County.count()]
    }

    def create = {
        def countyInstance = new County()
        countyInstance.properties = params
        return [countyInstance: countyInstance]
    }

    def save = {
        def countyInstance = new County(params)
        if (countyInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'county.label', default: 'County'), countyInstance.id])}"
            redirect(action: "show", id: countyInstance.id)
        }
        else {
            render(view: "create", model: [countyInstance: countyInstance])
        }
    }

    def show = {
        def countyInstance = County.get(params.id)
        if (!countyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'county.label', default: 'County'), params.id])}"
            redirect(action: "list")
        }
        else {
            [countyInstance: countyInstance]
        }
    }

    def edit = {
        def countyInstance = County.get(params.id)
        if (!countyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'county.label', default: 'County'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [countyInstance: countyInstance]
        }
    }

    def update = {
        def countyInstance = County.get(params.id)
        if (countyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (countyInstance.version > version) {
                    
                    countyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'county.label', default: 'County')] as Object[], "Another user has updated this County while you were editing")
                    render(view: "edit", model: [countyInstance: countyInstance])
                    return
                }
            }
            countyInstance.properties = params
            if (!countyInstance.hasErrors() && countyInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'county.label', default: 'County'), countyInstance.id])}"
                redirect(action: "show", id: countyInstance.id)
            }
            else {
                render(view: "edit", model: [countyInstance: countyInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'county.label', default: 'County'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def countyInstance = County.get(params.id)
        if (countyInstance) {
            try {
                countyInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'county.label', default: 'County'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'county.label', default: 'County'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'county.label', default: 'County'), params.id])}"
            redirect(action: "list")
        }
    }
}
