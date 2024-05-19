"use client";

import { useState } from "react";
import { useSearchParams, usePathname, useRouter } from "next/navigation";
import { useDebouncedCallback } from "use-debounce";
// import { MagnifyingGlassIcon } from '@heroicons/react/24/outline';

export default function Search({ placeholder }: { placeholder: string }) {
  const searchParams = useSearchParams();

  // use Next.js's useRouter and usePathname hooks to update the URL.
  const pathname = usePathname();
  const { replace } = useRouter();

  /*
    This function will wrap the contents of handleSearch,
    and only run the code after a specific time once the user has stopped typing (300ms).
        -> prevents updating the URL on every keystroke
  */
  // const handleSearch = useDebouncedCallback((term) => {
  //   /*
  //       URLSearchParams is a Web API that provides utility methods for manipulating the URL query parameters.
  //       Instead of creating a complex string literal, we can use it to get the params string like ?page=1&query=a
  //   */
  //   const params = new URLSearchParams(searchParams);

  //   if (term) {
  //     params.set("q", term.trim());
  //   } else {
  //     params.delete("q");
  //   }

  //   replace(`${pathname}?${params.toString()}`);
  // }, 300);

  const [searchTerm, setSearchTerm] = useState(searchParams.get("q") || "");

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const params = new URLSearchParams(searchParams);

    if (searchTerm.trim()) {
      params.set("q", searchTerm.trim());
    } else {
      params.delete("q");
    }

    replace(`/trips/?${params.toString()}`);
  };

  return (
    <form className="relative flex flex-1 w-full" onSubmit={handleSubmit}>
      {/* defaultValue vs. value / Controlled vs. Uncontrolled */}

      <label className="input input-bordered flex items-center gap-2 w-full">
        <input
          type="text"
          className="grow block w-full rounded-md"
          placeholder={placeholder}
          defaultValue={searchParams.get("query")?.toString()}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 16 16"
          fill="currentColor"
          className="w-4 h-4 opacity-70"
        >
          <path
            fillRule="evenodd"
            d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z"
            clipRule="evenodd"
          />
        </svg>
      </label>
    </form>
  );
}
