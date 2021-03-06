/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.xd.rest.client.domain;

import org.springframework.hateoas.ResourceSupport;

/**
 * Represents a stream, <i>i.e.</i> a flow of data in the system.
 * 
 * @author Eric Bottard
 * 
 */
public class Stream extends ResourceSupport {

	private String name;

	/**
	 * Default constructor for serialization frameworks.
	 */
	private Stream() {

	}

	public Stream(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
