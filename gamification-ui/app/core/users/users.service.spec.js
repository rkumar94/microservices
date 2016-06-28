/*
 * Copyright (c) 2016, juja.com.ua
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 *  * Neither the name of microservices nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Created by danil.kuznetsov on 18.06.16.
 */

(function () {
    "use strict";
    describe('Users', function () {
        var httpMockBackend, Users, scope;
        var usersMockData = [
            {
                email: 'example@example.com', skype: 'example.skype', slack: 'example.slack', total: 0
            },
            {
                email: 'example1@example.com', skype: 'example1.skype', slack: 'example1.slack', total: 20
            }
        ];

        var pointsMockData = [
            {UserFrom: 'example@example.com', HowMuch: '1', Description: 'Thanks for help with lab 1'},
            {UserFrom: 'example2@example.com', HowMuch: '10', Description: 'Thanks for help with lab 11'},
            {UserFrom: 'example3@example.com', HowMuch: '25', Description: 'Thanks for help with lab 3'},
            {UserFrom: 'example4@example.com', HowMuch: '3', Description: '	Thanks for help with lab 45'}
        ];


        beforeEach(function () {
            jasmine.addCustomEqualityTester(angular.equals);
        });

        beforeEach(module('core.users'));

        beforeEach(inject(function ($rootScope, _$httpBackend_, _Users_) {
            scope = $rootScope.$new();
            httpMockBackend = _$httpBackend_;
            Users = _Users_;
        }));

        it('should fetch the users data from `/api/users`', function () {
            httpMockBackend.whenGET('/api/users').respond(usersMockData);
            var users = Users.query();
            expect(users).toEqual([]);
            httpMockBackend.flush();
            expect(users).toEqual(usersMockData);
        });

        it('should fetch info of users whom received me points `api/users/1/receviedPoints`', function () {
            httpMockBackend.whenGET('/api/users/1/receivedPoints').respond(pointsMockData);
            var points = Users.findPoints({id:1,findPoints:'receivedPoints'});
            expect(points).toEqual([]);
            httpMockBackend.flush();
            expect(points).toEqual(pointsMockData);
        });

        it('should fetch info of users whom send points `/api/users/1/sentPoints`', function () {
            httpMockBackend.whenGET('/api/users/1/sentPoints').respond(pointsMockData);
            var points = Users.findPoints({id:1,findPoints:'sentPoints'});
            expect(points).toEqual([]);
            httpMockBackend.flush();
            expect(points).toEqual(pointsMockData);
        });
    });
})();